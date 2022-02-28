package com.nerds.stuppingmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nerds.stuppingmall.dto.MemberSignUpRequestDto;
import com.nerds.stuppingmall.service.member.MemberInfoService;
import com.nerds.stuppingmall.service.member.MemberRegisterService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SignController {
	final MemberRegisterService memberRegisterService;
	final MemberInfoService memberInfoService;
	
	@PostMapping("/signUp")
	public String save(MemberSignUpRequestDto memberSignUpRequestDto) {
		memberRegisterService.addMember(memberSignUpRequestDto);
		return "redirect:/";
	}

	@PostMapping("/findUserId")
	public @ResponseBody String findMemberId(String name, String phoneNum) {
		return memberInfoService.findMemberId(name, phoneNum);
	}

}
