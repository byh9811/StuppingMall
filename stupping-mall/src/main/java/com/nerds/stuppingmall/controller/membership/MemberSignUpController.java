package com.nerds.stuppingmall.controller.membership;

import com.nerds.stuppingmall.dto.CustomerSignUpRequestDto;
import com.nerds.stuppingmall.dto.SupplierSignUpRequestDto;
import com.nerds.stuppingmall.service.member.MemberRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberSignUpController {
    private final MemberRegisterService memberRegisterService;

    @PostMapping("/auth/register/supplier")
    public String registerSupplier(@Valid SupplierSignUpRequestDto supplierSignUpRequestDto) {
        memberRegisterService.addSupplier(supplierSignUpRequestDto);
        return "redirect:/";
    }

    @PostMapping("/auth/register/customer")
    public String registerCustomer(@Valid CustomerSignUpRequestDto customerSignUpRequestDto) {
        memberRegisterService.addCustomer(customerSignUpRequestDto);
        return "redirect:/";
    }

}
