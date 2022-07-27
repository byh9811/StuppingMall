package com.nerds.stuppingmall.repository.member;

import com.nerds.stuppingmall.domain.Customer;
import com.nerds.stuppingmall.domain.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@RequiredArgsConstructor
public class SupplierRepositoryImpl implements SupplierRepository {
    final MongoTemplate mongoTemplate;

    @Override
    public Supplier findSupplierByEmail(String email) {
        return mongoTemplate.findOne(new Query().addCriteria(Criteria.where("email").is(email)), Supplier.class, "members");
    }
}
