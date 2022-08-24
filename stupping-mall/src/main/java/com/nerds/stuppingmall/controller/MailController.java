package com.nerds.stuppingmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MailController {
    @GetMapping("/emailTestPage")
    public String emailTestPage() {
        // 미완.
        return "mailtest";
    }
}
