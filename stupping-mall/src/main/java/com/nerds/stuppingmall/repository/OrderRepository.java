package com.nerds.stuppingmall.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.nerds.stuppingmall.domain.Notebook;
import com.nerds.stuppingmall.domain.Order;

public interface OrderRepository extends MongoRepository<Order, String> {
}