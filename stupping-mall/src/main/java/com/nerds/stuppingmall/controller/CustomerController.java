package com.nerds.stuppingmall.controller;

import com.nerds.stuppingmall.dto.AccessibleInfoRequestDto;
import com.nerds.stuppingmall.dto.MyPageResponseDto;
import com.nerds.stuppingmall.dto.NotebookResponseBasicDto;
import com.nerds.stuppingmall.service.category.CategoryStatusService;
import com.nerds.stuppingmall.service.introduction.IntroductionSearchService;
import com.nerds.stuppingmall.service.knowhow.KnowhowSearchService;
import com.nerds.stuppingmall.service.notebook.NotebookSearchService;
import com.nerds.stuppingmall.service.order.OrderSearchService;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.domain.Order;
import com.nerds.stuppingmall.dto.Authentication;
import com.nerds.stuppingmall.service.member.MemberDeregisterService;
import com.nerds.stuppingmall.service.member.MemberDetailsService;
import com.nerds.stuppingmall.service.member.MemberModifyService;
import com.nerds.stuppingmall.service.order.OrderRegisterService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalTime;
import java.util.HashMap;
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
	final NotebookSearchService notebookSearchService;
	final IntroductionSearchService introductionSearchService;
	final KnowhowSearchService knowhowSearchService;
	final CategoryStatusService categoryStatusService;

	@GetMapping("/main")
	public String customerMain(@AuthenticationPrincipal Authentication authentication, Model model) {
		model.addAttribute("date", LocalTime.now());
		model.addAttribute("topNotebooks", notebookSearchService.getTop3Notebooks());
		model.addAttribute("recentNotebooks", notebookSearchService.getRecent8Notebooks());
		model.addAttribute("categories", categoryStatusService.getCategoryNames());
		model.addAttribute("introductions", introductionSearchService.getAllIntroductions());
		model.addAttribute("knowhows", knowhowSearchService.getAllKnowhows());

		return "customer/customerMain";
	}

	@GetMapping("/myOrders")
	public String getMyOrders(@AuthenticationPrincipal Authentication authentication, Model model) {
		List<Order> myOrders = orderSearchService.findMyOrders(authentication.getId());

		model.addAttribute("orders", myOrders);

		return "orderList";
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
		MyPageResponseDto myPageDto = memberDetailsService.getMyPage(authentication.getId());
		model.addAttribute("myInfo", myPageDto);
		return "memberInfo";
	}

	@PostMapping("/updateMyInfo")
	public String updateMyInfo(@AuthenticationPrincipal Authentication authentication, AccessibleInfoRequestDto accessibleInfoRequestDto) {
		memberModifyService.updateInfo(authentication.getId(), accessibleInfoRequestDto);
		return "redirect:/customer/myPage";
	}

	@PostMapping("/leave")
	public String leave(@AuthenticationPrincipal Authentication authentication) {
		memberDeregisterService.removeMember(authentication.getId());
		return "redirect:/logout";
	}

	@PostMapping("/addBalance")
	public String addBalance(@AuthenticationPrincipal Authentication authentication, int money) {
		memberModifyService.updateBalance(authentication.getId(), money);
		return "redirect:/customer/myPage";
	}
	
	@PostMapping("/makeOrder")
	public String makeOrder(@AuthenticationPrincipal Authentication authentication, String notebookId, int payment, Model model) {
		Order order = orderRegisterService.addOrder(authentication.getId(), notebookId, payment);
		Order[] orders = new Order[1];
		orders[0] = order;
		model.addAttribute("orders", orders);
		return "orderInfo";
	}
}