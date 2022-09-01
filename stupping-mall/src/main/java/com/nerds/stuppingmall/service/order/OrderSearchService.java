package com.nerds.stuppingmall.service.order;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.domain.Order;
import com.nerds.stuppingmall.dto.OrderSalesInfoResponseDto;
import com.nerds.stuppingmall.repository.order.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderSearchService {
	final OrderRepository orderRepository;
	final long MIN_VALUE_FOR_GRAPH = 10;
	
	public List<Order> findMyOrders(String customerId) {
		Pageable pageable = PageRequest.of(0, 12);
		return orderRepository.findOrdersByCustomerId(pageable, customerId);
	}
	
	public OrderSalesInfoResponseDto findSales(String duration, String notebookId) {
		Map<String, Long> salesMap;
		
		switch(duration) {
		case "week": salesMap = orderRepository.customCountWeekSalesByNotebookId(notebookId); break;
		case "month": salesMap = orderRepository.customCountMonthSalesByNotebookId(notebookId); break;
		case "halfYear": salesMap = orderRepository.customCountHalfYearSalesByNotebookId(notebookId); break;
		default: throw new RuntimeException();
		}
		
		Long max = MIN_VALUE_FOR_GRAPH;
		for(Long sales: salesMap.values())
			max = Math.max(sales, max);
		
		return OrderSalesInfoResponseDto.builder()
										.maxValue(max)
										.sales(salesMap)
										.build();
	}
}