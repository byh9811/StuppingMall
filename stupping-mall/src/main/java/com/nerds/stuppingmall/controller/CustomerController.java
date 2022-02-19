package com.nerds.stuppingmall.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.domain.Order;
import com.nerds.stuppingmall.dto.Authentication;
import com.nerds.stuppingmall.service.member.MemberDeregisterService;
import com.nerds.stuppingmall.service.member.MemberDetailsService;
import com.nerds.stuppingmall.service.member.MemberModifyService;
import com.nerds.stuppingmall.service.order.OrderRegisterService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
	final MemberDetailsService memberDetailsService;
	final MemberDeregisterService memberDeregisterService;
	final MemberModifyService memberModifyService;
	final OrderRegisterService orderRegisterService;

	@GetMapping("/myPage")
	public String MyPageDetail(@AuthenticationPrincipal Authentication authentication, Model model) {
		Member member = memberDetailsService.findMemberById(authentication.getId());
		Member[] members = new Member[1];
		members[0] = member;
		model.addAttribute("members", members);
		return "memberInfo";
	}
	
	@PostMapping("/leave")
	public String leave(@AuthenticationPrincipal Authentication authentication) {
		memberDeregisterService.removeMember(authentication.getId());
		return "redirect:/logout";
	}
	
	@PostMapping("/changePassword")
	public String changePassword(@AuthenticationPrincipal Authentication authentication, String newPassword) {
		memberModifyService.updatePassword(authentication.getId(), newPassword);
		return "redirect:/logout";
	}
	
	@PostMapping("/addBalance")
	public String addBalance(@AuthenticationPrincipal Authentication authentication, int money) {
		memberModifyService.updateBalance(authentication.getId(), money);
		return "redirect:/customer/myPage";
	}
	
	@PostMapping("/makeOrder")
	public String makeOrder(@AuthenticationPrincipal Authentication authentication, String notebookId, int payment, Model model) {
		Order order = orderRegisterService.addOrder(authentication.getId(), notebookId, payment);
		Order[] orders = new Order[1];
		orders[0] = order;
		model.addAttribute("orders", orders);
		return "orderInfo";
	}
}