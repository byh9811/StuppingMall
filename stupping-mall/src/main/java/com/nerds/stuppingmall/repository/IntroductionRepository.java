package com.nerds.stuppingmall.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nerds.stuppingmall.domain.Introduction;

public interface IntroductionRepository extends MongoRepository<Introduction, Integer> {
}
