package com.nerds.stuppingmall.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.dto.Authentication;
import com.nerds.stuppingmall.service.member.MemberDeregisterService;
import com.nerds.stuppingmall.service.member.MemberDetailsService;
import com.nerds.stuppingmall.service.member.MemberModifyService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
	final MemberDetailsService memberDetailsService;
	final MemberDeregisterService memberDeregisterService;
	final MemberModifyService memberModifyService;

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
	
	@PostMapping("changePassword")
	public String changePassword(@AuthenticationPrincipal Authentication authentication, String newPassword) {
		memberModifyService.updatePassword(authentication.getId(), newPassword);
		return "redirect:/logout";
	}
	
}