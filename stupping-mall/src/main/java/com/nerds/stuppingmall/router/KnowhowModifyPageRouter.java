package com.nerds.stuppingmall.router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KnowhowModifyPageRouter {
    @GetMapping("/knowhowModifyPage")
    public String knowhowModifyPage() {
        return "knowhowModifyPage";
    }
}
