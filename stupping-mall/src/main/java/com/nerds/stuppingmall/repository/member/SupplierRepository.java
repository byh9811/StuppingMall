package com.nerds.stuppingmall.repository.member;

import com.nerds.stuppingmall.domain.Customer;
import com.nerds.stuppingmall.domain.Supplier;

public interface SupplierRepository {
    Supplier findSupplierByEmail(String email);
    Supplier save(Supplier supplier);
}
