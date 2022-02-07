package com.nerds.stuppingmall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nerds.stuppingmall.dto.Authentication;
import com.nerds.stuppingmall.dto.NotebookAddRequestDto;
import com.nerds.stuppingmall.dto.NotebookInfoResponseDto;
import com.nerds.stuppingmall.service.MemberService;
import com.nerds.stuppingmall.service.NotebookService;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
	@Autowired
	MemberService memberService;
	@Autowired
	NotebookService notebookService;
	
	@PostMapping("/addNotebook")
	public String addNotebook(@AuthenticationPrincipal Authentication authentication, NotebookAddRequestDto notebookAddRequestDto) {
		if(notebookAddRequestDto.getGpuName().isEmpty())
			notebookAddRequestDto.setGpuName("내장 그래픽");
		String id = notebookService.addNotebook(authentication.getId(), notebookAddRequestDto);
		return "redirect:/notebookInfo?id=" + id;
	}
	
	@GetMapping("/myNotebooks")
	public String myNotebooks(@AuthenticationPrincipal Authentication authentication, Model model) {
		List<NotebookInfoResponseDto> notebooks = notebookService.getMyNotebooks(authentication.getId());
		model.addAttribute("notebooks", notebooks);
		
		return "notebookInfo";
	}
	
	@PostMapping("/deleteNotebook")
	public String deleteNotebook(@AuthenticationPrincipal Authentication authentication, String productId) {
		notebookService.deleteNotebook(authentication.getId(), productId);
		
		return "redirect:/myNotebooks";
	}
}