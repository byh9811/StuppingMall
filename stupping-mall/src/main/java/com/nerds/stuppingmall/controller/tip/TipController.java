package com.nerds.stuppingmall.controller.tip;

import com.nerds.stuppingmall.dto.CustomerSignUpRequestDto;
import com.nerds.stuppingmall.dto.SupplierSignUpRequestDto;
import com.nerds.stuppingmall.enumerate.Role;
import com.nerds.stuppingmall.service.member.MemberRegisterService;
import com.nerds.stuppingmall.service.tip.TipSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalTime;

@Controller
@RequiredArgsConstructor
public class TipController {
    final TipSearchService tipSearchService;

    @GetMapping("/tips")
    public String enterTipPage(Model model) {
        // 미완.
        model.addAttribute("date", LocalTime.now());
        model.addAttribute("tips", tipSearchService.getAllTips());

        return "common/new8";
    }
}
