package com.nerds.stuppingmall.controller.tip;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminTipController {
    @GetMapping("/knowhowModifyPage")
    public String knowhowModifyPage() {
        //미완.

        return "knowhowModifyPage";
    }
}