package com.nerds.stuppingmall.controller;

import com.nerds.stuppingmall.dto.Authentication;
import com.nerds.stuppingmall.service.member.MemberModifyService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nerds.stuppingmall.dto.MemberSignUpRequestDto;
import com.nerds.stuppingmall.service.member.MemberInfoService;
import com.nerds.stuppingmall.service.member.MemberRegisterService;

import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class SignController {
	final MemberRegisterService memberRegisterService;
	final MemberInfoService memberInfoService;
	final MemberModifyService memberModifyService;
	
	@PostMapping("/signUp")
	public String save(MemberSignUpRequestDto memberSignUpRequestDto) {
		memberRegisterService.addMember(memberSignUpRequestDto);
		return "redirect:/";
	}

	@PostMapping("/findUserId")
	public @ResponseBody String findMemberId(HttpServletRequest request, String email, String key) {
		if(request.getSession().getAttribute(email).equals(key))
			return "redirect:/error/incorrectKey";
		return memberInfoService.findMemberId(email);
	}

	@PostMapping("/changePassword")
	public String changePassword(HttpServletRequest request, @AuthenticationPrincipal Authentication authentication, String email, String key, String newPassword) {
		if(request.getSession().getAttribute(email).equals(key))
			return "redirect:/error/incorrectKey";
		else if(authentication == null)
			return "redirect:/error/error401";
		memberModifyService.updatePassword(authentication.getId(), newPassword);
		return "redirect:/logout";
	}

}
