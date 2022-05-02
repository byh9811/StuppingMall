package com.nerds.stuppingmall.controller;

import java.time.LocalTime;
import java.util.List;

import com.nerds.stuppingmall.dto.NotebookInfoResponseSimpleDto;
import com.nerds.stuppingmall.dto.NotebookResponseBasicDto;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nerds.stuppingmall.dto.Authentication;
import com.nerds.stuppingmall.dto.NotebookInfoRequestDto;
import com.nerds.stuppingmall.enumerate.Role;
import com.nerds.stuppingmall.service.category.CategoryStatusService;
import com.nerds.stuppingmall.service.introduction.IntroductionSearchService;
import com.nerds.stuppingmall.service.knowhow.KnowhowSearchService;
import com.nerds.stuppingmall.service.notebook.NotebookSearchService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PageController {
	final CategoryStatusService categoryStatusService;
	final NotebookSearchService notebookSearchService;
	final IntroductionSearchService introductionSearchService;
	final KnowhowSearchService knowhowSearchService;

	@GetMapping("/")
	public String main(@AuthenticationPrincipal Authentication authentication) {
		String redirectURI;

		if(authentication == null) {
			redirectURI = "/login";
		} else if(authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_CUSTOMER"))) {
			redirectURI = "/customer/main";
		} else if(authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_SUPPLIER"))) {
			redirectURI = "/supplier/main";
		} else if(authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
			redirectURI = "/admin/main";
		} else {
			redirectURI = "/error/forbidden";
			System.out.println("error occured");
		}

		return "redirect:"+redirectURI;
	}

	@GetMapping("/signUpPage")
	public String signUpPage(Model model) {
		model.addAttribute("date", LocalTime.now());
		model.addAttribute("roles", Role.values());
		return "common/signUp";
	}
	
	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("date", LocalTime.now());
		return "common/login";
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

	@GetMapping("changePasswordWithoutLogin")
	public String changePasswordWithoutLogin(Model model) {
		model.addAttribute("date", LocalTime.now());
		return "common/changePasswordWithoutLogin";
	}
	
	@GetMapping("/notebookAddPage")
	public String notebookAddPage() {
		return "notebookAddPage";
	}

	@GetMapping("/introductionModifyPage")
	public String introductionModifyPage() {
		return "introductionModifyPage";
	}

	@GetMapping("/knowhowModifyPage")
	public String knowhowModifyPage() {
		return "knowhowModifyPage";
	}

	@GetMapping("/emailTestPage")
	public String emailTestPage() {
		return "mailtest";
	}

}