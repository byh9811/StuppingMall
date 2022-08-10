package com.nerds.stuppingmall.controller.signUp;

import com.nerds.stuppingmall.dto.CustomerSignUpRequestDto;
import com.nerds.stuppingmall.service.member.MemberRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class CustomerSignUpController {
    private final MemberRegisterService memberRegisterService;

    @PostMapping("/signUp")
    public String save(@Valid CustomerSignUpRequestDto customerSignUpRequestDto) {
        memberRegisterService.addCustomer(customerSignUpRequestDto);
        return "redirect:/";
    }

}
