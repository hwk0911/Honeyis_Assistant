package com.cafecoder.tistory.user;

import com.cafecoder.tistory.user.dto.UsersSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsersApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UsersRepository usersRepository;

    @After
    public void tearDown() throws Exception {
        usersRepository.deleteAll();
    }

    @Test
    public void UsersSingUp () throws Exception {
        //given
        String userId = "hwk0911";
        String password = "1234";
        String company = "cafeCoder";
        String email = "hwk0911@gmail.com";

        UsersSaveRequestDto requestDto = UsersSaveRequestDto.builder()
                .userId(userId)
                .password(password)
                .company(company)
                .email(email)
                .build();

        String url = "http://localhost:" + port + "/api/v1/users";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Users> all = usersRepository.findAll();
        assertThat(all.get(0).getUserId()).isEqualTo(userId);
        assertThat(all.get(0).getPassword()).isEqualTo(password);
        assertThat(all.get(0).getCompany()).isEqualTo(company);

    }
}