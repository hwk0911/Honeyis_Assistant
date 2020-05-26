package com.cafecoder.tistory.user;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UsersRepository userRepository;

    @After
    public void cleanup() {
        userRepository.deleteAll();
    }

    @Test
    public void userSave() {
        //given
        String userId = "hwk0911";
        String password = "test";
        String company = "cafeCoderInc";
        String email = "hwk0911@gmail.com";

        userRepository.save(Users.builder()
                .userId(userId)
                .password(password)
                .company(company)
                .build());

        //when
        List<Users> usersList = userRepository.findAll();

        //then
        Users users = usersList.get(0);
        assertThat(users.getUserId()).isEqualTo(userId);
        assertThat(users.getPassword()).isEqualTo(password);
        assertThat(users.getCompany()).isEqualTo(company);
    }
}