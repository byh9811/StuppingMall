package com.nerds.stuppingmall.controller;

import com.nerds.stuppingmall.dto.Authentication;
import com.nerds.stuppingmall.service.member.MemberModifyService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.nerds.stuppingmall.dto.MemberSignUpRequestDto;
import com.nerds.stuppingmall.service.member.MemberInfoService;
import com.nerds.stuppingmall.service.member.MemberRegisterService;

import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class SignController {
	final MemberRegisterService memberRegisterService;
	final MemberInfoService memberInfoService;
	final MemberModifyService memberModifyService;
	
	@PostMapping("/signUp")
	public String save(@Valid MemberSignUpRequestDto memberSignUpRequestDto) {
		memberRegisterService.addMember(memberSignUpRequestDto);
		return "redirect:/";
	}

	@GetMapping("/userId")
	public @ResponseBody String findMemberId(HttpServletRequest request, @RequestParam("email") String email, @RequestParam("key") String key) {
		if(request.getSession().getAttribute(email).equals(key))
			return "redirect:/error/incorrectKey";
		return memberInfoService.findMemberId(email);
	}

	@PutMapping("/password")
	public String changePassword(HttpServletRequest request, @AuthenticationPrincipal Authentication authentication, @RequestParam("email") String email, @RequestParam("key") String key, @RequestParam("newPassword") String newPassword) {
		if(request.getSession().getAttribute(email).equals(key))
			return "redirect:/error/incorrectKey";
		else if(authentication == null)
			return "redirect:/error/error401";
		memberModifyService.updatePassword(authentication.getId(), newPassword);
		return "redirect:/logout";
	}

}
