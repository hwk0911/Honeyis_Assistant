package com.cafecoder.tistory.files;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RequiredArgsConstructor
@RestController
public class FilesApiController {

    @PostMapping("api/va/filesup")
    public void filesup (@PathVariable String name) {
        System.out.println(name);
    }
}

/*
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
 */