package com.nerds.stuppingmall.controller.member;

import com.nerds.stuppingmall.service.bank.BankDeregisterService;
import com.nerds.stuppingmall.service.bank.BankModifyService;
import com.nerds.stuppingmall.service.bank.BankRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminMemberController {
    @GetMapping("/memberBanPage")
    public String memberBanPage() {
        // 미완

        return "memberBanPage";
    }
}