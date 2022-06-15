package com.nerds.stuppingmall.router;

import com.nerds.stuppingmall.dto.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageRouter {
    @GetMapping("/")
    public String main(@AuthenticationPrincipal Authentication authentication) {
        String redirectURI;

        if(authentication == null) {
            redirectURI = "/login";
        } else if(authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_CUSTOMER"))) {
            redirectURI = "/customer/main";
        } else if(authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_SUPPLIER"))) {
            redirectURI = "/supplier/main";
        } else if(authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            redirectURI = "/admin/main";
        } else {
            redirectURI = "/error/forbidden";
            System.out.println("error occured");
        }

        return "redirect:"+redirectURI;
    }
}
