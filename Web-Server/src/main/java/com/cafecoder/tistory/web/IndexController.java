package com.cafecoder.tistory.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index (HttpSession session) {
        //if(session.)

        return "index";
    }

    @GetMapping("/users/signup")
    public String signUp () {
        return "signup";
    }

    @GetMapping("/users/signin")
    public String signIn () {
        return"signin";
    }
}