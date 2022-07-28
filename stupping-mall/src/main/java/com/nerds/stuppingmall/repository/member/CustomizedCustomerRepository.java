package com.nerds.stuppingmall.repository.member;

import com.nerds.stuppingmall.domain.Customer;
import org.springframework.stereotype.Repository;

public interface CustomizedCustomerRepository {
    Customer findCustomerByEmail(String email);
}
