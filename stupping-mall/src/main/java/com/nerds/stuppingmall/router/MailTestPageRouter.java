package com.nerds.stuppingmall.router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MailTestPageRouter {
    @GetMapping("/emailTestPage")
    public String emailTestPage() {
        return "mailtest";
    }
}
