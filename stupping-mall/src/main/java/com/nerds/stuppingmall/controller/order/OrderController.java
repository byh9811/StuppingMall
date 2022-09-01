package com.nerds.stuppingmall.controller.order;

import com.nerds.stuppingmall.domain.Notebook;
import com.nerds.stuppingmall.domain.Order;
import com.nerds.stuppingmall.dto.Authentication;
import com.nerds.stuppingmall.dto.NotebookDto;
import com.nerds.stuppingmall.service.notebook.NotebookDetailsService;
import com.nerds.stuppingmall.service.order.OrderModifyService;
import com.nerds.stuppingmall.service.order.OrderRegisterService;
import com.nerds.stuppingmall.service.order.OrderSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
	private final NotebookDetailsService notebookDetailsService;
	private final OrderSearchService orderSearchService;
	private final OrderRegisterService orderRegisterService;

	@GetMapping("/myOrders")
	public String getMyOrders(@AuthenticationPrincipal Authentication authentication, Model model) {
		// 마이페이지에서 클릭했을때 페이지로 넘기기로 했으므로 orders로 페이지 만들어서 넘겨야됨.
		List<Order> myOrders = orderSearchService.findMyOrders(authentication.getId());

		model.addAttribute("orders", myOrders);

		return "orderInfo";
	}

	@GetMapping("/order/index/items/{id}")
	public String getProductsInfoByCategory(@PathVariable("id") String itemId, Model model) {
		// 노트북 말고 DTO 새로 생성해서 리턴해야함. 지금은 임시. orderPage에 뭐 들어갈지 정해야 만들 수 있을듯.
		Notebook notebook = notebookDetailsService.findNotebook(itemId);

		model.addAttribute("notebook", notebook);

		return "orderPage";
	}

	@PostMapping("/order")
	public String addOrder(@AuthenticationPrincipal Authentication authentication, @RequestParam("itemId") String itemId, @RequestParam("payment") int payment, Model model) {
		Order order = orderRegisterService.addOrder(authentication.getId(), itemId, payment);
		model.addAttribute("order", order);
		return "orderCompletePage";
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
