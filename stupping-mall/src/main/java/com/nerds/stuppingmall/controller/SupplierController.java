package com.nerds.stuppingmall.controller;

import com.nerds.stuppingmall.domain.Introduction;
import com.nerds.stuppingmall.dto.NotebookResponseBasicDto;
import com.nerds.stuppingmall.service.category.CategoryStatusService;
import com.nerds.stuppingmall.service.introduction.IntroductionSearchService;
import com.nerds.stuppingmall.service.knowhow.KnowhowSearchService;
import com.nerds.stuppingmall.service.order.OrderModifyService;
import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.nerds.stuppingmall.domain.Order;
import com.nerds.stuppingmall.dto.Authentication;
import com.nerds.stuppingmall.dto.NotebookAddRequestDto;
import com.nerds.stuppingmall.dto.NotebookListResponseDto;
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
	final KnowhowSearchService knowhowSearchService;

	@GetMapping("/main")
	public String supplierMain(@AuthenticationPrincipal Authentication authentication, Model model) {
		model.addAttribute("date", LocalTime.now());
		model.addAttribute("recentNotebooks", notebookSearchService.getNew8Notebooks());
		model.addAttribute("introductions", introductionSearchService.getAllIntroductions());
		model.addAttribute("knowhows", knowhowSearchService.getAllKnowhows());

		Page<NotebookResponseBasicDto> notebookResponseBasicDtoPages = notebookSearchService.getMyNotebook(0, "최신순", authentication.getId());
		model.addAttribute("myNotebooks", notebookResponseBasicDtoPages.getContent());
		model.addAttribute("curPage", notebookResponseBasicDtoPages.getNumber());
		model.addAttribute("maxPage", notebookResponseBasicDtoPages.getTotalPages());

		return "supplierMain";
	}

	@PostMapping("/notebook")
	public String addNotebook(@AuthenticationPrincipal Authentication authentication, @RequestParam("notebook") NotebookAddRequestDto notebookAddRequestDto) {
		if(notebookAddRequestDto.getGpuName().isEmpty())
			notebookAddRequestDto.setGpuName("내장 그래픽");
		String id = notebookRegisterService.addNotebook(authentication.getId(), notebookAddRequestDto);
		return "redirect:/notebookInfo?id=" + id;
	}
	
	@GetMapping("/myNotebooks")
	public String getMyNotebooks(@RequestParam("page") int curPage, @RequestParam("order") String sortingOrder, @AuthenticationPrincipal Authentication authentication, Model model) {
		Page<NotebookListResponseDto> notebookPages = notebookSearchService.findNotebooksBySupplierId(curPage, sortingOrder, authentication.getId());
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