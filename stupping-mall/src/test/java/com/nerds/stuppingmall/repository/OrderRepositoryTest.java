package com.nerds.stuppingmall.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.nerds.stuppingmall.StuppingMallApplication;
import com.nerds.stuppingmall.domain.Order;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = StuppingMallApplication.class)
@DirtiesContext
@DataMongoTest
public class OrderRepositoryTest {
	@Autowired
	MongoTemplate mongoTemplate;
	@Autowired
	OrderRepository orderRepository;
	
	final int SIZE_PER_PAGE = 10;
	
//	@Test
	public void saveOrder() {
		for(int i=1; i<20; i++) {
			// given
			Order order = Order.builder()
						.customerId("byh9811")
						.supplierId("Samsung")
						.notebookId("notebookId"+i)
						.status("주문완료")
						.payDate(LocalDate.now().toString())
						.deliveredDate(null)
						.payment(500000)
						.build();
			
			// when
			Order savedOrder = mongoTemplate.save(order, "orders");
			
			// then
			assertEquals(order, savedOrder);
		}
	}
	
	@Test
	public void readOrder() {
		// given
		int curPage = 0;
		String customerId = "byh9811";
		
		// when
		Pageable pageable = PageRequest.of(curPage, SIZE_PER_PAGE);
		List<Order> orderPages = orderRepository.findOrdersByCustomerId(pageable, customerId);
		
		// then
		for(Order order: orderPages.getContent())
			System.out.println(order.getNotebookId());
		assertEquals(curPage, orderPages.getNumber());
		assertEquals(SIZE_PER_PAGE, orderPages.getSize());
	}
}
