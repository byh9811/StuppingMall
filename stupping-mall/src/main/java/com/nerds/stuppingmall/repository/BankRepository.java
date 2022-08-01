package com.nerds.stuppingmall.repository;

import com.nerds.stuppingmall.domain.Bank;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BankRepository extends MongoRepository<Bank, String> {
}
