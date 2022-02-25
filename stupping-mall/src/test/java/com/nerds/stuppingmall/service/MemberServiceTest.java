//package com.nerds.stuppingmall.repository;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.data.domain.Page;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Query;
//
//import com.nerds.stuppingmall.domain.Order;
//import com.nerds.stuppingmall.service.order.OrderSearchService;
//
//@ExtendWith(MockitoExtension.class)
//public class OrderSearchServiceTest {
//	@InjectMocks
//	OrderSearchService orderSearchService;
//	
//	@Mock
//	MongoTemplate mongoTemplate;
//	
//	@Test
//	public void read() {
//		// given
//		int curPage = 1;
//		String customerId = "byh9811";
//		Query query = new Query();
//		when(mongoTemplate.find(query, Order.class, "orders"))
//			.thenReturn(null)
//		
//		// when
//		Page<Order> result = orderSearchService.findMyOrders(curPage, customerId);
//		
//		// then
//		assertEquals();
//	}
//}
