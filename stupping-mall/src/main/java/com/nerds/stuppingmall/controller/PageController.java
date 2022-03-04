package com.nerds.stuppingmall.controller;

import java.time.LocalTime;

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
	CategoryStatusService categoryStatusService;
	NotebookSearchService notebookSearchService;
	IntroductionSearchService introductionSearchService;
	KnowhowSearchService knowhowSearchService;
	
	@GetMapping("/")
	public String main(@AuthenticationPrincipal Authentication authentication, Model model) {
		model.addAttribute("date", LocalTime.now());
		model.addAttribute("recentNotebooks", notebookSearchService.getRecent8Notebooks());
		model.addAttribute("categories", categoryStatusService.getExistingCategories());
		model.addAttribute("introductions", introductionSearchService.getAllIntroductions());
		model.addAttribute("knowhows", knowhowSearchService.getAllKnowhows());
		if(authentication != null && authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("SELLER"))) {
			
		}
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
	
	@GetMapping("/notebookCategorySearchPage")
	public String notebookCategorySearchPage(Model model) {
		NotebookInfoRequestDto categories = categoryStatusService.getExistingCategories();
		model.addAttribute("supplierNames", categories.getSupplierNames());
		model.addAttribute("cpuNames", categories.getCpuNames());
		model.addAttribute("gpuNames", categories.getGpuNames());
		model.addAttribute("manufactureYears", categories.getRegisterYears());
		return "notebookCategorySearchPage";
	}
}