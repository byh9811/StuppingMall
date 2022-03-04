package com.nerds.stuppingmall.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nerds.stuppingmall.domain.Knowhow;

public interface KnowhowRepository extends MongoRepository<Knowhow, Integer> {
}
