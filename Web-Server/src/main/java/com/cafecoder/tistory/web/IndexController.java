package com.cafecoder.tistory.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index () {
        return "index";
    }

    @GetMapping("/users/signup")
    public String signUp () {
        return "signUp";
    }

    @GetMapping("/users/signin")
    public String signIn () {
        return"signin";
    }
}