package com.nerds.stuppingmall.repository.member;

import com.nerds.stuppingmall.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String>, CustomizedCustomerRepository {
    Customer findByEmail(String email);
}