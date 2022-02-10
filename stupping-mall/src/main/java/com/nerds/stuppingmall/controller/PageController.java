package com.nerds.stuppingmall.controller;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nerds.stuppingmall.dto.Authentication;
import com.nerds.stuppingmall.enumerate.Role;
import com.nerds.stuppingmall.service.CategoryService;
import com.nerds.stuppingmall.service.MemberService;

@Controller
public class PageController {
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/")
	public String main(Model model) {
		model.addAttribute("date", LocalTime.now());
		return "main";
	}

	@GetMapping("/signUpPage")
	public String signUpPage(Model model) {
		model.addAttribute("roles", Role.values());
		return "signUp";
	}
	
	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("date", LocalTime.now());
		return "signIn";
	}
	
	@GetMapping("/memberBanPage")
	public String memberBanPage() {
		return "memberBanPage";
	}
	
	@GetMapping("/userIdFindPage")
	public String userIdFindPage() {
		return "userIdFindPage";
	}
	
	@GetMapping("/passwordChangePage")
	public String passwordChangePage(@AuthenticationPrincipal Authentication authentication, Model model) {
		model.addAttribute("id", authentication.getId());
		return "passwordChangePage";
	}
	
	@GetMapping("/notebookAddPage")
	public String notebookAddPage() {
		return "notebookAddPage";
	}
}