package com.nerds.stuppingmall.repository.member;

import com.nerds.stuppingmall.domain.Customer;
import com.nerds.stuppingmall.domain.Supplier;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SupplierRepository extends MongoRepository<Supplier, String>, CustomizedSupplierRepository {
    Supplier findByEmail(String email);
}