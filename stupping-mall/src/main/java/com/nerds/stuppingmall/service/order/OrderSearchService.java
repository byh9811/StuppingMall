package com.nerds.stuppingmall.service.order;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.domain.Notebook;
import com.nerds.stuppingmall.domain.Order;
import com.nerds.stuppingmall.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderSearchService {
	final MongoTemplate mongoTemplate;
	final int SIZE_PER_PAGE = 10;
	
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
}
