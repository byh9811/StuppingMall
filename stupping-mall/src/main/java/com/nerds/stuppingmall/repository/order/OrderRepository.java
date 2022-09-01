package com.nerds.stuppingmall.repository.order;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.nerds.stuppingmall.domain.Order;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String>, CustomizedOrderRepository {
    @Query(sort="{_id=-1}")
    List<Order> findOrdersByCustomerId(Pageable pageable, String customerId);
}