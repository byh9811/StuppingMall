package com.nerds.stuppingmall.repository.member;

import com.nerds.stuppingmall.domain.Customer;

public interface CustomerRepository {
    Customer findCustomerByEmail(String email);
}
