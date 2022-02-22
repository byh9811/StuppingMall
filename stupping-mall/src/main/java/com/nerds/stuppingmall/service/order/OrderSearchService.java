package com.nerds.stuppingmall.service.order;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.domain.Order;
import com.nerds.stuppingmall.dto.OrderSalesInfoResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderSearchService {
	final MongoTemplate mongoTemplate;
	final int SIZE_PER_PAGE = 10;
	final long MIN_VALUE_FOR_GRAPH = 10;
	
	public Page<Order> findMyOrders(int curPage, String id) {
		Pageable pageable = PageRequest.of(curPage, 10);
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		query.with(pageable);

		List<Order> myOrders = mongoTemplate.find(query, Order.class, "orders");
		return PageableExecutionUtils.getPage(
				myOrders, pageable,
				() -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), Order.class));
	}
	
	public OrderSalesInfoResponseDto findWeekSales(String notebookId) {
		Long max = MIN_VALUE_FOR_GRAPH;
		HashMap<String, Long> map = new HashMap<>();
		Criteria criteria = Criteria.where("notebookId").is(notebookId);
		for(int i=1; i<8; i++) {
			String date = LocalDate.now().minusDays(i).toString();
			criteria.andOperator(Criteria.where("payDate").is(date));
			Long sales = mongoTemplate.count(new Query().addCriteria(criteria), Order.class, "orders");
			map.put(date, sales);
			max = Math.max(max, sales);
		}
		
		return OrderSalesInfoResponseDto.builder()
										.maxValue(max)
										.sales(map)
										.build();
	}
	
	public OrderSalesInfoResponseDto findMonthSales(String notebookId) {
		Long max = MIN_VALUE_FOR_GRAPH;
		HashMap<String, Long> map = new HashMap<>();
		Criteria criteria = Criteria.where("notebookId").is(notebookId);
		for(int i=1, j=7; i<30; i+=7, j=+7) {
			String startDate = LocalDate.now().minusDays(i).toString();
			String endDate = LocalDate.now().minusDays(j).toString();
			criteria.andOperator(
					Criteria.where("payDate").gte(startDate)
					.orOperator(Criteria.where("payDate").lte(endDate)));
			Long sales = mongoTemplate.count(new Query().addCriteria(criteria), Order.class, "orders");
			map.put(startDate + "~" + endDate, sales);
			max = Math.max(max, sales);
		}
		
		return OrderSalesInfoResponseDto.builder()
										.maxValue(max)
										.sales(map)
										.build();
	}
	
	public OrderSalesInfoResponseDto findHalfYearSales(String notebookId) {
		Long max = MIN_VALUE_FOR_GRAPH;
		HashMap<String, Long> map = new HashMap<>();
		Criteria criteria = Criteria.where("notebookId").is(notebookId);
		for(int i=1; i<7; i++) {
			String date = LocalDate.now().minusMonths(i).toString().substring(0, 7);
			criteria.andOperator(Criteria.where("payDate").regex(date+".*"));
			Long sales = mongoTemplate.count(new Query().addCriteria(criteria), Order.class, "orders");
			map.put(date, sales);
			max = Math.max(max, sales);
		}
		
		return OrderSalesInfoResponseDto.builder()
										.maxValue(max)
										.sales(map)
										.build();
	}
}