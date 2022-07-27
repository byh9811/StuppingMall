package com.nerds.stuppingmall.controller;

import com.nerds.stuppingmall.dto.CustomerMyPageModifyRequest;
import com.nerds.stuppingmall.dto.CustomerMyPageResponse;
import com.nerds.stuppingmall.service.order.OrderModifyService;
import com.nerds.stuppingmall.service.order.OrderSearchService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.nerds.stuppingmall.domain.Order;
import com.nerds.stuppingmall.dto.Authentication;
import com.nerds.stuppingmall.service.member.MemberDeregisterService;
import com.nerds.stuppingmall.service.member.MemberDetailsService;
import com.nerds.stuppingmall.service.member.MemberModifyService;
import com.nerds.stuppingmall.service.order.OrderRegisterService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
	final MemberDetailsService memberDetailsService;
	final MemberDeregisterService memberDeregisterService;
	final MemberModifyService memberModifyService;
	final OrderRegisterService orderRegisterService;
	final OrderSearchService orderSearchService;
	final OrderModifyService orderModifyService;

	@GetMapping("/myOrders")
	public String getMyOrders(@AuthenticationPrincipal Authentication authentication, Model model) {
		List<Order> myOrders = orderSearchService.findMyOrders(authentication.getId());

		model.addAttribute("orders", myOrders);

		return "orderInfo";
	}

	@GetMapping("/myPicks")
	public String getMyPicks(@AuthenticationPrincipal Authentication authentication, Model model) {
		List<String> myPicks = memberDetailsService.getMyPicks(authentication.getId());
		model.addAttribute("picks", myPicks);
		return "pickList";
	}

	@GetMapping("/myRecentFinds")
	public String myRecentFinds(@AuthenticationPrincipal Authentication authentication, Model model) {
		List<String> myFinds = memberDetailsService.getMyRecentFinds(authentication.getId());

		model.addAttribute("recentFinds", myFinds);

		return "recentFindList";
	}

	@GetMapping("/myPage")
	public String MyPageDetail(@AuthenticationPrincipal Authentication authentication, Model model) {
		CustomerMyPageResponse myPageDto = memberDetailsService.getMyPage(authentication.getId());
		model.addAttribute("myInfo", myPageDto);
		return "/customer/myPage";
	}

	@PutMapping("/updateMyInfo")
	public String updateMyInfo(@AuthenticationPrincipal Authentication authentication, @RequestParam("info") CustomerMyPageModifyRequest accessibleInfoRequestDto) {
		memberModifyService.updateInfo(authentication.getId(), accessibleInfoRequestDto);
		return "redirect:/customer/myPage";
	}

	@DeleteMapping("/leave")
	public String leave(@AuthenticationPrincipal Authentication authentication) {
		memberDeregisterService.removeMember(authentication.getId());
		return "redirect:/logout";
	}

	@PutMapping("/addBalance")
	public String addBalance(@AuthenticationPrincipal Authentication authentication, @RequestParam("money") int money) {
		memberModifyService.updateBalance(authentication.getId(), money);
		return "redirect:/customer/myPage";
	}
	
	@PostMapping("/makeOrder")
	public String makeOrder(@AuthenticationPrincipal Authentication authentication, @RequestParam("notebookId") String notebookId, @RequestParam("payment") int payment, Model model) {
		Order order = orderRegisterService.addOrder(authentication.getId(), notebookId, payment);
		Order[] orders = new Order[1];
		orders[0] = order;
		model.addAttribute("orders", orders);
		return "orderInfo";
	}

	@PostMapping("/objection/{id}")
	public String makeObjection(@AuthenticationPrincipal Authentication authentication, @PathVariable("id") String orderId, Model model) {
		Order order = orderModifyService.makeObjection(orderId);
		Order[] orders = new Order[1];
		orders[0] = order;
		model.addAttribute("orders", orders);
		return "orderInfo";
	}
}