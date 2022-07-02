package com.nerds.stuppingmall.router;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalTime;

@Controller
public class LoginPageRouter {
    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("date", LocalTime.now());
        return "common/signIn";
    }
}
