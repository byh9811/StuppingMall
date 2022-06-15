package com.nerds.stuppingmall.router;

import com.nerds.stuppingmall.dto.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PasswordChangeWithAuthPageRouter {
    @GetMapping("/passwordChangePage")
    public String passwordChangePage(@AuthenticationPrincipal Authentication authentication, Model model) {
        model.addAttribute("id", authentication.getId());
        return "passwordChangePage";
    }
}
