package com.nerds.stuppingmall.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nerds.stuppingmall.domain.Order;

public interface OrderRepository extends MongoRepository<Order, String> {
}