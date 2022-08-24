package com.nerds.stuppingmall.controller.member;

import com.nerds.stuppingmall.dto.Authentication;
import com.nerds.stuppingmall.dto.CustomerSignUpRequestDto;
import com.nerds.stuppingmall.dto.SupplierSignUpRequestDto;
import com.nerds.stuppingmall.enumerate.Role;
import com.nerds.stuppingmall.service.member.MemberRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalTime;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberRegisterService memberRegisterService;

    @GetMapping("/login/index")
    public String enterLoginPage(Model model) {
        model.addAttribute("date", LocalTime.now());
        return "common/signIn";
    }

    @GetMapping("/register/index")
    public String enterSignUpPage(Model model) {
        model.addAttribute("date", LocalTime.now());
        model.addAttribute("roles", Role.values());
        return "common/signUp";
    }

    @GetMapping("/idInquiry/index")
    public String enterUserIdFindPage() {
        // 미완. id찾기가 가능하려면 email인증 말고 다른 방법이 필요함.

        return "common/findId";
    }

    @GetMapping("/pwInquiry/index")
    public String enterChangePasswordPage(Model model) {
        model.addAttribute("date", LocalTime.now());
        return "common/changePasswordWithoutLogin";
    }

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
