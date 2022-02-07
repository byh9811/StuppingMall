package com.nerds.stuppingmall.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nerds.stuppingmall.domain.Category;

public interface CategoryRepository extends MongoRepository<Category, String> {
}
