package com.nerds.stuppingmall.router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserIdFindPageRouter {
    @GetMapping("/userIdFindPage")
    public String userIdFindPage() {
        return "common/findId";
    }
}
