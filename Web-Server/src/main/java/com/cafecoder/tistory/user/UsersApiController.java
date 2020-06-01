package com.cafecoder.tistory.user;

import com.cafecoder.tistory.service.UsersService;
import com.cafecoder.tistory.user.dto.UsersSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@RequiredArgsConstructor
@RestController
public class UsersApiController {

    private final UsersService usersService;
    private final UsersRepository usersRepository;

    @PostMapping("/api/v1/signup")
    public Long signup (@RequestBody UsersSaveRequestDto requestDto) {

        return usersService.save(requestDto);
    }

    @PostMapping("/api/v1/signin")
    public Long signin(@RequestBody Map<String, String> userData, HttpSession session) {
        String userId = userData.get("userId");
        String userPassword = userData.get("password");

        System.out.println(userId);
        System.out.println(userPassword);

        Users user = this.usersRepository.findByUserId(userId);

        if(user == null) {
            System.out.println("login failed");
            return null;
        }
        if(!user.getPassword().equals(userPassword)) {
            System.out.println("login failed");
            return null;
        }

        session.setAttribute("id", userId);

        return user.getId();
    }
}
