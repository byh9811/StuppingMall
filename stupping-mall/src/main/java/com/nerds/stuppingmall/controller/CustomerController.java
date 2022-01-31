package com.nerds.stuppingmall.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.dto.Authentication;
import com.nerds.stuppingmall.dto.MemberSignUpRequestDto;
import com.nerds.stuppingmall.service.MemberService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	MemberService memberService;

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