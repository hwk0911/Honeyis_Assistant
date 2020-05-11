package com.cafecoder.tistory.user;

import com.cafecoder.tistory.web.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @PostMapping("/create")
    public String create (User user) {
        System.out.println(user.toString());

        return "signIn";
    }
}
