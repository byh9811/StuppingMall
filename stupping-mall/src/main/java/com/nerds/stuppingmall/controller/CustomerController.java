package com.nerds.stuppingmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.dto.Authentication;
import com.nerds.stuppingmall.service.MemberService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	MemberService memberService;

	@GetMapping("/myPage")
	public String getMyPage(@AuthenticationPrincipal Authentication authentication, Model model) {
		Member member = memberService.getMyPage(authentication.getId());
		Member[] members = new Member[1];
		members[0] = member;
		model.addAttribute("members", members);
		return "memberInfo";
	}
	
	@PostMapping("/leave")
	public String leave(@AuthenticationPrincipal Authentication authentication) {
		memberService.leave(authentication.getId());
		return "redirect:/logout";
	}
	
	@PostMapping("changePassword")
	public String changePassword(@AuthenticationPrincipal Authentication authentication, String newPassword) {
		memberService.updatePassword(authentication.getId(), newPassword);
		return "redirect:/logout";
	}
	
}