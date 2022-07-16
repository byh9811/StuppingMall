package com.nerds.stuppingmall.controller;

import com.nerds.stuppingmall.dto.*;
import com.nerds.stuppingmall.service.introduction.IntroductionSearchService;
import com.nerds.stuppingmall.service.tip.TipSearchService;
import com.nerds.stuppingmall.service.order.OrderModifyService;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.nerds.stuppingmall.domain.Order;
import com.nerds.stuppingmall.service.notebook.NotebookDeregisterService;
import com.nerds.stuppingmall.service.notebook.NotebookRegisterService;
import com.nerds.stuppingmall.service.notebook.NotebookSearchService;
import com.nerds.stuppingmall.service.order.OrderSearchService;

import lombok.RequiredArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/supplier")
public class SupplierController {
	final NotebookRegisterService notebookRegisterService;
	final NotebookSearchService notebookSearchService;
	final NotebookDeregisterService notebookDeregisterService;
	final OrderSearchService orderSearchService;
	final OrderModifyService orderModifyService;
	final IntroductionSearchService introductionSearchService;
	final TipSearchService tipSearchService;

	@PostMapping("/notebook")
	public String addNotebook(@AuthenticationPrincipal Authentication authentication, @RequestParam("notebook") NotebookDto.Request notebookRequestDto) {
		if(notebookRequestDto.getGpuName().isEmpty())
			notebookRequestDto.setGpuName("내장 그래픽");
		String id = notebookRegisterService.addNotebook(authentication.getId(), notebookRequestDto);
		return "redirect:/notebookInfo?id=" + id;
	}
	
	@GetMapping("/myNotebooks")
	public String getMyNotebooks(@RequestParam(value = "page", defaultValue = "0") int curPage,
								 @RequestParam(value = "order", defaultValue = "최신순") String sortingOrder,
								 @RequestParam(value = "name", defaultValue = "") String name,
								 @AuthenticationPrincipal Authentication authentication, Model model) {
		Page<NotebookDto.IdNameResponse> notebookPages = notebookSearchService.getMyNotebook(curPage, sortingOrder, authentication.getId(), name);
		model.addAttribute("notebooks", notebookPages.getContent());
		model.addAttribute("curPage", notebookPages.getNumber());
		model.addAttribute("maxPage", notebookPages.getTotalPages());
		
		return "notebookInfo";
	}
	
	@DeleteMapping("/notebook/{id}")
	public String deleteNotebook(@AuthenticationPrincipal Authentication authentication, @PathVariable("id") String notebookId) {
		notebookDeregisterService.removeNotebook(authentication.getId(), notebookId);
		
		return "redirect:/myNotebooks";
	}
	
	// 추후 수정 필요
	@GetMapping("/myOrders")
	public @ResponseBody List<Order> getMyOrders(@AuthenticationPrincipal Authentication authentication, Model model) {
		List<Order> myOrders = orderSearchService.findMyOrders(authentication.getId());
		return myOrders;
	}
	
	@PutMapping("/order/{id}")
	public String acceptOrder(@AuthenticationPrincipal Authentication authentication, @PathVariable("id") String orderId, @RequestParam("action") String action, @RequestParam(value = "reason", required = false, defaultValue = " ") String reason) throws Exception {
		switch (action) {
			case "accept": orderModifyService.acceptOrder(orderId); break;
			case "deny": orderModifyService.denyOrder(orderId); break;
			case "start": orderModifyService.startDelivery(orderId); break;
			case "finish": orderModifyService.finishDelivery(orderId); break;
			case "complete": orderModifyService.completeOrder(orderId); break;
			case "acceptObjection": orderModifyService.acceptObjection(orderId); break;
			case "denyObjection": orderModifyService.denyObjection(orderId, reason); break;
			default: throw new Exception("incorrectAction");
		}

		return "redirect:/myNotebooks";
	}
}