package com.nerds.stuppingmall.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nerds.stuppingmall.domain.Tip;

public interface TipRepository extends MongoRepository<Tip, Integer> {
}
