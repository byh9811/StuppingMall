package com.nerds.stuppingmall.repository.member;

import com.nerds.stuppingmall.domain.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {
    final MongoTemplate mongoTemplate;

    @Override
    public Customer findCustomerByEmail(String email) {
        return mongoTemplate.findOne(new Query().addCriteria(Criteria.where("_id").is(email)), Customer.class, "members");
    }
}
