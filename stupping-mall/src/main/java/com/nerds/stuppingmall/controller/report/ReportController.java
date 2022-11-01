package com.nerds.stuppingmall.controller.report;

import com.nerds.stuppingmall.domain.Notebook;
import com.nerds.stuppingmall.domain.Order;
import com.nerds.stuppingmall.dto.Authentication;
import com.nerds.stuppingmall.service.notebook.NotebookDetailsService;
import com.nerds.stuppingmall.service.order.OrderModifyService;
import com.nerds.stuppingmall.service.order.OrderRegisterService;
import com.nerds.stuppingmall.service.order.OrderSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReportController {
	private final OrderModifyService orderModifyService;

	@GetMapping("/report/index")
	public String enterReportPage() {
		return "reportPage";
	}

	@PostMapping("/report")
	public String makeObjection(@AuthenticationPrincipal Authentication authentication, @PathVariable("id") String orderId, Model model) {
		Order order = orderModifyService.makeObjection(orderId);
		Order[] orders = new Order[1];
		orders[0] = order;
		model.addAttribute("orders", orders);
		return "orderInfo";
	}

	@GetMapping("/report/done")
	public String checkReport() {
		return "reportResult";
	}

//  물건이 도착하지 않았거나 이상이 있는데 도착 완료 처리가 된 경우 이의를 제기하기 위한 메서드 -> 신고 기능으로 대체?
//	@PostMapping("/objection/{id}")
//	public String makeObjection(@AuthenticationPrincipal Authentication authentication, @PathVariable("id") String orderId, Model model) {
//		Order order = orderModifyService.makeObjection(orderId);
//		Order[] orders = new Order[1];
//		orders[0] = order;
//		model.addAttribute("orders", orders);
//		return "orderInfo";
//	}
}
