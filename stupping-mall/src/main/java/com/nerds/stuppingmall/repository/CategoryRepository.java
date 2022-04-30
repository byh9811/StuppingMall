package com.nerds.stuppingmall.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nerds.stuppingmall.domain.Category;

import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, String> {
    List<String> findAllId();
}
