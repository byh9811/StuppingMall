package com.nerds.stuppingmall.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nerds.stuppingmall.dto.Authentication;
import com.nerds.stuppingmall.dto.NotebookAddRequestDto;
import com.nerds.stuppingmall.dto.NotebookInfoResponseDto;
import com.nerds.stuppingmall.service.notebook.NotebookDeregisterService;
import com.nerds.stuppingmall.service.notebook.NotebookRegisterService;
import com.nerds.stuppingmall.service.notebook.NotebookSearchService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/supplier")
public class SupplierController {
	final NotebookRegisterService notebookRegisterService;
	final NotebookSearchService notebookSearchService;
	final NotebookDeregisterService notebookDeregisterService;
	
	@PostMapping("/addNotebook")
	public String addNotebook(@AuthenticationPrincipal Authentication authentication, NotebookAddRequestDto notebookAddRequestDto) {
		if(notebookAddRequestDto.getGpuName().isEmpty())
			notebookAddRequestDto.setGpuName("내장 그래픽");
		String id = notebookRegisterService.addNotebook(authentication.getId(), notebookAddRequestDto);
		return "redirect:/notebookInfo?id=" + id;
	}
	
	@GetMapping("/myNotebooks")
	public String myNotebooks(@AuthenticationPrincipal Authentication authentication, Model model) {
		Page<NotebookInfoResponseDto> notebookPages = notebookSearchService.findNotebooksBySupplierId(0, authentication.getId());
		model.addAttribute("notebooks", notebookPages.getContent());
		model.addAttribute("curPage", notebookPages.getNumber());
		model.addAttribute("maxPage", notebookPages.getTotalPages());
		
		return "notebookInfo";
	}
	
	@PostMapping("/deleteNotebook")
	public String deleteNotebook(@AuthenticationPrincipal Authentication authentication, String productId) {
		notebookDeregisterService.removeNotebook(authentication.getId(), productId);
		
		return "redirect:/myNotebooks";
	}
}