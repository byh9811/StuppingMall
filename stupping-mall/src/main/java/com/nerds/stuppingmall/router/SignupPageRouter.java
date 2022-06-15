package com.nerds.stuppingmall.router;

import com.nerds.stuppingmall.enumerate.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalTime;

@Controller
public class SignupPageRouter {
    @GetMapping("/signUpPage")
    public String signUpPage(Model model) {
        model.addAttribute("date", LocalTime.now());
        model.addAttribute("roles", Role.values());
        return "common/signUp";
    }
}
