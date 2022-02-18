package com.nerds.stuppingmall.service.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.domain.Order;
import com.nerds.stuppingmall.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderSearchService {
	final OrderRepository orderRepository;
	final int SIZE_PER_PAGE = 10;
	
	public Page<Order> findMyOrders(int curPage, String id) {
		Pageable pageable = PageRequest.of(curPage, 10);
		return orderRepository.findAll(pageable);
	}
}
