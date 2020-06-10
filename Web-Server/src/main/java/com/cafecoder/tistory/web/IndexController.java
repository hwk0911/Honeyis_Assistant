package com.cafecoder.tistory.web;

import com.cafecoder.tistory.files.Stocks;
import com.cafecoder.tistory.files.StocksRepository;
import com.cafecoder.tistory.user.Users;
import com.cafecoder.tistory.user.UsersRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private String sessionId;
    private String userId;
    private final StocksRepository stocksRepository;
    private final UsersRepository usersRepository;

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        if (!session.getAttributeNames().hasMoreElements()) {
            return "redirect:/users/signin";
        }

        this.sessionId = session.getAttributeNames().nextElement();
        this.userId = session.getAttribute(this.sessionId).toString();
        Users user = this.usersRepository.findByUserId(this.userId);

        model.addAttribute("users", user);

        return "index";
    }

    @GetMapping("/users/signout")
    public String signout(HttpSession session) {
        session.removeAttribute(userId);

        return "redirect:/users/signin";
    }

    @GetMapping("/users/signup")
    public String signUp(HttpSession session) {
        if(this.userId != null) {
            return "redirect:/";
        }

        return "signup";
    }

    @GetMapping("/users/signin")
    public String signIn(HttpSession session) {
        if(this.userId != null) {
            return "redirect:/";
        }

        return "signin";
    }

    @GetMapping("/users/stock")
    public String stock(HttpSession session, Model model) {
        if (this.userId == null) {
            return "redirect:/";
        }
        Users users = this.usersRepository.findByUserId(this.userId);

        model.addAttribute("stocks", this.stocksRepository.findByUserId(this.userId));
        model.addAttribute("users", users);

        return "stocks";
    }

    @GetMapping("/users/stockList")
    public String stockList(HttpSession session, Model model) {
        if (this.userId == null) {
            return "redirect:/";
        }
        Users users = this.usersRepository.findByUserId(this.userId);

        model.addAttribute("stocks", this.stocksRepository.findByUserId(this.userId));
        model.addAttribute("users", users);

        return "stockList";
    }

    public String getUserId() {
        return userId;
    }
}