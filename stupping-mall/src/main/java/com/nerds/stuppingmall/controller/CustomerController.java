package com.nerds.stuppingmall.controller;

import com.nerds.stuppingmall.dto.AccessibleInfoRequestDto;
import com.nerds.stuppingmall.dto.MyPageResponseDto;
import com.nerds.stuppingmall.dto.NotebookResponseBasicDto;
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

	@GetMapping("/main")
	public String customerMain(@AuthenticationPrincipal Authentication authentication, Model model) {
		model.addAttribute("date", LocalTime.now());
		model.addAttribute("recentNotebooks", notebookSearchService.getRecent8Notebooks());
		model.addAttribute("introductions", introductionSearchService.getAllIntroductions());
		model.addAttribute("knowhows", knowhowSearchService.getAllKnowhows());

		return "customerMain";
	}

	@GetMapping("/myOrders")
	public @ResponseBody HashMap<String, List<Order>> getMyOrders(@AuthenticationPrincipal Authentication authentication) {
		List<Order> myOrders = orderSearchService.findMyOrders(authentication.getId());

		HashMap<String, List<Order>> json = new HashMap<>();
		json.put("orders", myOrders);

		return json;
	}

	@GetMapping("/myPicks")
	public @ResponseBody HashMap<String, List<String>> getMyPicks(@AuthenticationPrincipal Authentication authentication) {
		List<String> myPicks = memberDetailsService.getMyPicks(authentication.getId());

		HashMap<String, List<String>> json = new HashMap<>();
		json.put("picks", myPicks);

		return json;
	}

	@GetMapping("/myRecentFinds")
	public @ResponseBody HashMap<String, List<String>> myRecentFinds(@AuthenticationPrincipal Authentication authentication) {
		List<String> myFinds = memberDetailsService.getMyRecentFinds(authentication.getId());

		HashMap<String, List<String>> json = new HashMap<>();
		json.put("finds", myFinds);

		return json;
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