package com.nerds.stuppingmall.repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;

import com.nerds.stuppingmall.domain.Order;

public class CustomizedOrderRepositoryImpl implements CustomizedOrderRepository {
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public Page<Order> customFindOrdersByCustomerId(Pageable pageable, String customerId) {
		Query query = new Query(Criteria.where("customerId").is(customerId));
		query.with(pageable);

		List<Order> myOrders = mongoTemplate.find(query, Order.class, "orders");
		return PageableExecutionUtils.getPage(
				myOrders, pageable,
				() -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), Order.class));
	}

	@Override
	public Map<String, Long> customCountWeekSalesByNotebookId(String notebookId) {
		Map<String, Long> map = new HashMap<>();
		Criteria criteria = Criteria.where("notebookId").is(notebookId);
		for(int i=1; i<8; i++) {
			String date = LocalDate.now().minusDays(i).toString();
			criteria.andOperator(Criteria.where("payDate").is(date));
			Long sales = mongoTemplate.count(new Query().addCriteria(criteria), Order.class, "orders");
			map.put(date, sales);
		}
		return map;
	}

	@Override
	public Map<String, Long> customCountMonthSalesByNotebookId(String notebookId) {
		Map<String, Long> map = new HashMap<>();
		Criteria criteria = Criteria.where("notebookId").is(notebookId);
		for(int i=1, j=7; i<30; i+=7, j=+7) {
			String startDate = LocalDate.now().minusDays(i).toString();
			String endDate = LocalDate.now().minusDays(j).toString();
			criteria.andOperator(
					Criteria.where("payDate").gte(startDate)
					.orOperator(Criteria.where("payDate").lte(endDate)));
			Long sales = mongoTemplate.count(new Query().addCriteria(criteria), Order.class, "orders");
			map.put(startDate + "~" + endDate, sales);
		}
		return map;
	}

	@Override
	public Map<String, Long> customCountHalfYearSalesByNotebookId(String notebookId) {
		Map<String, Long> map = new HashMap<>();
		Criteria criteria = Criteria.where("notebookId").is(notebookId);
		for(int i=1; i<7; i++) {
			String date = LocalDate.now().minusMonths(i).toString().substring(0, 7);
			criteria.andOperator(Criteria.where("payDate").regex(date+".*"));
			Long sales = mongoTemplate.count(new Query().addCriteria(criteria), Order.class, "orders");
			map.put(date, sales);
		}
		return map;
	}
}
