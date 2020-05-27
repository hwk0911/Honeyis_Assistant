package com.cafecoder.tistory.web;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    private String userId;

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        System.out.println(session.getId());

        if (!session.getAttributeNames().hasMoreElements()) {
            return "redirect:/users/signin";
        }

        this.userId = session.getAttributeNames().nextElement();
        System.out.println(session.getAttribute(userId));

        model.addAttribute(session.getAttribute(userId));

        return "index";
    }

    @GetMapping("/users/signout")
    public String signout(HttpSession session) {
        session.removeAttribute(userId);

        return "redirect:/users/signin";
    }

    @GetMapping("/users/signup")
    public String signUp() {
        return "signup";
    }

    @GetMapping("/users/signin")
    public String signIn() {
        return "signin";
    }
}