package com.nerds.stuppingmall.controller.member;

import com.nerds.stuppingmall.domain.Report;
import com.nerds.stuppingmall.dto.*;
import com.nerds.stuppingmall.enumerate.Role;
import com.nerds.stuppingmall.service.member.MemberDetailsService;
import com.nerds.stuppingmall.service.member.MemberModifyService;
import com.nerds.stuppingmall.service.member.MemberRegisterService;
import com.nerds.stuppingmall.service.report.ReportSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberRegisterService memberRegisterService;
    private final MemberModifyService memberModifyService;
    private final MemberDetailsService memberDetailsService;
    private final ReportSearchService reportSearchService;

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

    @GetMapping("/customer/index")
    public String enterMyPage(@AuthenticationPrincipal Authentication authentication, Model model) {
        CustomerMyPageResponse myPageDto = memberDetailsService.getMyPage(authentication.getId());
        model.addAttribute("myInfo", myPageDto);
        return "/customer/myPage";
    }

    @GetMapping("/idInquiry/{email}")
    public @ResponseBody String findMemberId(HttpServletRequest request, @PathVariable("email") String email, @RequestParam("key") String key) {
        // 미완. id찾기가 가능하려면 email인증 말고 다른 방법이 필요함.
        if(request.getSession().getAttribute(email).equals(key))
            return "redirect:/error/incorrectKey";
        return "redirect:/";
    }

    @GetMapping("/pwInquiry/index")
    public String enterChangePasswordPage(Model model) {
        model.addAttribute("date", LocalTime.now());
        return "common/changePasswordWithoutLogin";
    }

    @PutMapping("/pwInquiry/{email}")
    public String changePassword(HttpServletRequest request,
                                 @PathVariable("email") String email,
                                 @RequestParam("key") String key,
                                 @RequestParam("newPassword") String newPassword) {
        if(request.getSession().getAttribute(email).equals(key))
            return "redirect:/error/incorrectKey";
        memberModifyService.updatePassword(email, newPassword);
        return "redirect:/logout";
    }

    @PutMapping("/customer")
    public String changeCustomerInfo(@AuthenticationPrincipal Authentication authentication, @RequestParam("info") CustomerMyPageModifyRequest accessibleInfoRequestDto) {
        memberModifyService.updateInfo(authentication.getId(), accessibleInfoRequestDto);
        return "redirect:/customer/index";
    }

    @GetMapping("/customer/myPicks")
    public HashMap<String, List<String>> getMyPicks(@AuthenticationPrincipal Authentication authentication) {
        HashMap<String, List<String>> json = new HashMap<>();
        json.put("myPicks", memberDetailsService.getMyPicks(authentication.getId()));
        return json;
    }

    @GetMapping("/customer/recentFinds")
    public HashMap<String, List<String>> getRecentFinds(@AuthenticationPrincipal Authentication authentication) {
        HashMap<String, List<String>> json = new HashMap<>();
        json.put("recentFinds", memberDetailsService.getMyRecentFinds(authentication.getId()));
        return json;
    }

    @GetMapping("/customer/recentFinds")
    public HashMap<String, List<Report>> getMyReports(@AuthenticationPrincipal Authentication authentication) {
        HashMap<String, List<Report>> json = new HashMap<>();
        json.put("reports", reportSearchService.findMyReports(authentication.getId()));
        return json;
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
