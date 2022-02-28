package com.nerds.stuppingmall.controller;

import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nerds.stuppingmall.domain.Order;
import com.nerds.stuppingmall.dto.Authentication;
import com.nerds.stuppingmall.dto.NotebookAddRequestDto;
import com.nerds.stuppingmall.dto.NotebookInfoResponseDto;
import com.nerds.stuppingmall.service.notebook.NotebookDeregisterService;
import com.nerds.stuppingmall.service.notebook.NotebookRegisterService;
import com.nerds.stuppingmall.service.notebook.NotebookSearchService;
import com.nerds.stuppingmall.service.order.OrderSearchService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/supplier")
public class SupplierController {
	final NotebookRegisterService notebookRegisterService;
	final NotebookSearchService notebookSearchService;
	final NotebookDeregisterService notebookDeregisterService;
	final OrderSearchService orderSearchService;
	
	@PostMapping("/addNotebook")
	public String addNotebook(@AuthenticationPrincipal Authentication authentication, NotebookAddRequestDto notebookAddRequestDto) {
		if(notebookAddRequestDto.getGpuName().isEmpty())
			notebookAddRequestDto.setGpuName("내장 그래픽");
		String id = notebookRegisterService.addNotebook(authentication.getId(), notebookAddRequestDto);
		return "redirect:/notebookInfo?id=" + id;
	}
	
	@GetMapping("/myNotebooks")
	public String getMyNotebooks(int curPage, String sortingOrder, @AuthenticationPrincipal Authentication authentication, Model model) {
		Page<NotebookInfoResponseDto> notebookPages = notebookSearchService.findNotebooksBySupplierId(curPage, sortingOrder, authentication.getId());
		model.addAttribute("notebooks", notebookPages.getContent());
		model.addAttribute("curPage", notebookPages.getNumber());
		model.addAttribute("maxPage", notebookPages.getTotalPages());
		
		return "notebookInfo";
	}
	
	@PostMapping("/deleteNotebook")
	public String deleteNotebook(@AuthenticationPrincipal Authentication authentication, String notebookId) {
		notebookDeregisterService.removeNotebook(authentication.getId(), notebookId);
		
		return "redirect:/myNotebooks";
	}
	
	@GetMapping("/myOrders")
	public String getMyOrders(@AuthenticationPrincipal Authentication authentication, int curPage, Model model) {
		Page<Order> orderPages = orderSearchService.findMyOrders(curPage, authentication.getId());
		model.addAttribute("orders", orderPages.getContent());
		model.addAttribute("curPage", orderPages.getNumber());
		model.addAttribute("maxPage", orderPages.getTotalPages());
		
		// 완성해야함
		return "redirect:/myOrders";
	}
	
	@PostMapping("/acceptOrder")
	public String acceptOrder(@AuthenticationPrincipal Authentication authentication, String notebookId) {
		notebookDeregisterService.removeNotebook(authentication.getId(), notebookId);
		
		return "redirect:/myNotebooks";
	}
}