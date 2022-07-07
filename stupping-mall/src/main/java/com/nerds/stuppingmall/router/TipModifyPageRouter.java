package com.nerds.stuppingmall.router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TipModifyPageRouter {
    @GetMapping("/knowhowModifyPage")
    public String knowhowModifyPage() {
        return "knowhowModifyPage";
    }
}
