package com.nerds.stuppingmall.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nerds.stuppingmall.domain.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, String> {
    //List<String> findAllId();
}
