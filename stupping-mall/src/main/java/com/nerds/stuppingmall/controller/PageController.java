package com.nerds.stuppingmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.dto.MemberDto;
import com.nerds.stuppingmall.service.MemberService;
import com.nerds.stuppingmall.service.Role;

@Controller
public class PageController {
	@Autowired
	MemberService memberService;
	
	@GetMapping("/")
	public String main() {
		return "main";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "signIn";
	}
	
	@GetMapping("/userDeletePage")
	public String userDeletePage() {
		return "userDeletePage";
	}
	
	@GetMapping("/userIdFindPage")
	public String userIdFindPage() {
		return "userIdFindPage";
	}
	
	@GetMapping("/passwordFindPage")
	@PostMapping("/passwordFindPage")
	public String passwordFindPage() {
		return "passwordFindPage";
	}
	
	@GetMapping("/member/passwordChangePage")
	public String passwordChangePage(@AuthenticationPrincipal MemberDto memberDto, Model model) {
		model.addAttribute("memberDto", memberDto);
		return "passwordChangePage";
	}
	
	@GetMapping("/memberAddPage")
	public String memberAddPage(Model model) {
		model.addAttribute("roles", Role.values());
		return "memberAddPage";
	}
}