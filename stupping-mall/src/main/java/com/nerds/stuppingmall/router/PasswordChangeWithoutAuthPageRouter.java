package com.nerds.stuppingmall.router;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalTime;

@Controller
public class PasswordChangeWithoutAuthPageRouter {
    @GetMapping("/changePasswordWithoutLogin")
    public String changePasswordWithoutLogin(Model model) {
        model.addAttribute("date", LocalTime.now());
        return "common/changePasswordWithoutLogin";
    }
}
