package com.nerds.stuppingmall.router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IntroductionModifyPageRouter {
    @GetMapping("/introductionModifyPage")
    public String introductionModifyPage() {
        return "introductionModifyPage";
    }
}
