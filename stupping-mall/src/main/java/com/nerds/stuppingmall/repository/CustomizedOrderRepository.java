package com.nerds.stuppingmall.repository;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nerds.stuppingmall.domain.Order;

public interface CustomizedOrderRepository {
	Page<Order> customFindOrdersByCustomerId(Pageable pageable, String customerId);
	Map<String, Long> customCountWeekSalesByNotebookId(String notebookId);
	Map<String, Long> customCountMonthSalesByNotebookId(String notebookId);
	Map<String, Long> customCountHalfYearSalesByNotebookId(String notebookId);
}
