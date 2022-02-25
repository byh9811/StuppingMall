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

import com.mongodb.assertions.Assertions;
import com.nerds.stuppingmall.StuppingMallApplication;
import com.nerds.stuppingmall.domain.Notebook;
import com.nerds.stuppingmall.domain.Order;
import com.nerds.stuppingmall.dto.NotebookInfoResponseDto;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = StuppingMallApplication.class)
@DirtiesContext
@DataMongoTest
public class NotebookRepositoryTest {
	@Autowired
	MongoTemplate mongoTemplate;
	@Autowired
	NotebookRepository notebookRepository;
	
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
		String supplierId = "Samsung";
		Query query = new Query();
		query.addCriteria(Criteria.where("supplierId").is(supplierId));
		
		// when
		List<NotebookInfoResponseDto> myNotebooks = mongoTemplate.find(query, NotebookInfoResponseDto.class, "notebooks");
		
		// then
		assertEquals(1, myNotebooks.size());
		assertEquals("삼성 갤럭시북 15", myNotebooks.get(0).getName());
	}
}
