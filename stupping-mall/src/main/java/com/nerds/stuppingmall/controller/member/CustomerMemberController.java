package com.nerds.stuppingmall.controller.member;

import com.nerds.stuppingmall.domain.Order;
import com.nerds.stuppingmall.domain.Report;
import com.nerds.stuppingmall.dto.*;
import com.nerds.stuppingmall.enumerate.Role;
import com.nerds.stuppingmall.service.member.MemberDeregisterService;
import com.nerds.stuppingmall.service.member.MemberDetailsService;
import com.nerds.stuppingmall.service.member.MemberModifyService;
import com.nerds.stuppingmall.service.member.MemberRegisterService;
import com.nerds.stuppingmall.service.order.OrderModifyService;
import com.nerds.stuppingmall.service.order.OrderRegisterService;
import com.nerds.stuppingmall.service.report.ReportSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/customer")
public class CustomerMemberController {
    private final MemberRegisterService memberRegisterService;
    private final MemberModifyService memberModifyService;
    private final MemberDetailsService memberDetailsService;
    private final ReportSearchService reportSearchService;
    private final MemberDeregisterService memberDeregisterService;
    private final OrderRegisterService orderRegisterService;
    private final OrderModifyService orderModifyService;

    @GetMapping("/index")
    public String enterMyPage(@AuthenticationPrincipal Authentication authentication, Model model) {
        CustomerMyPageResponse myPageDto = memberDetailsService.getMyPage(authentication.getId());
        model.addAttribute("myInfo", myPageDto);
        return "/customer/myPage";
    }

    @PutMapping
    public String changeCustomerInfo(@AuthenticationPrincipal Authentication authentication, @RequestParam("info") CustomerMyPageModifyRequest accessibleInfoRequestDto) {
        memberModifyService.updateInfo(authentication.getId(), accessibleInfoRequestDto);
        return "redirect:/customer/index";
    }

    @DeleteMapping
    public String leave(@AuthenticationPrincipal Authentication authentication) {
        memberDeregisterService.removeMember(authentication.getId());
        return "redirect:/logout";
    }

    @GetMapping("/myPicks")
    public HashMap<String, List<String>> getMyPicks(@AuthenticationPrincipal Authentication authentication) {
        HashMap<String, List<String>> json = new HashMap<>();
        json.put("myPicks", memberDetailsService.getMyPicks(authentication.getId()));
        return json;
    }

    @GetMapping("/recentFinds")
    public HashMap<String, List<String>> getRecentFinds(@AuthenticationPrincipal Authentication authentication) {
        HashMap<String, List<String>> json = new HashMap<>();
        json.put("recentFinds", memberDetailsService.getMyRecentFinds(authentication.getId()));
        return json;
    }

    @GetMapping("/reports")
    public HashMap<String, List<Report>> getMyReports(@AuthenticationPrincipal Authentication authentication) {
        HashMap<String, List<Report>> json = new HashMap<>();
        json.put("reports", reportSearchService.findMyReports(authentication.getId()));
        return json;
    }

    @PutMapping("/balance")
    public String addBalance(@AuthenticationPrincipal Authentication authentication, @RequestParam("money") int money) {
        memberModifyService.updateBalance(authentication.getId(), money);
        return "redirect:/customer/myPage";
    }

}
