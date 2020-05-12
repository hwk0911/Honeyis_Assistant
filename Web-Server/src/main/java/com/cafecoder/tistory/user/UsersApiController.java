package com.cafecoder.tistory.user;

import com.cafecoder.tistory.service.UsersService;
import com.cafecoder.tistory.user.dto.UsersSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UsersApiController {

    private final UsersService usersService;

    @PostMapping("/api/v1/users")
    public Long signup (@RequestBody UsersSaveRequestDto requestDto) {

        return usersService.save(requestDto);
    }
}
