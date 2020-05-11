package com.cafecoder.tistory.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String signIn () {
        return "signIn";
    }

    @GetMapping("/signup")
    public String signUp () {
        return "signUp";
    }

    @GetMapping("/CSS/signin.css")
    public String Css () {
        return "/templates/sign.css";
    }
}