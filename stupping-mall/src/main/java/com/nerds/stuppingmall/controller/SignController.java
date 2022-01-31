package com.nerds.stuppingmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nerds.stuppingmall.dto.MemberSignUpRequestDto;
import com.nerds.stuppingmall.service.MemberService;

@Controller
public class SignController {
	@Autowired
	MemberService memberService;
	
	@PostMapping("/signUp")
	public String save(MemberSignUpRequestDto memberSignUpRequestDto) {
		memberService.insertMember(memberSignUpRequestDto);
		return "redirect:/";
	}

	@PostMapping("/findUserId")
	public @ResponseBody String findUserId(String name, String phoneNum) {
		return memberService.findUserId(name, phoneNum);
	}

}
