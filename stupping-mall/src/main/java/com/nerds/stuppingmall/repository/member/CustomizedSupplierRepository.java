package com.nerds.stuppingmall.repository.member;

import com.nerds.stuppingmall.domain.Supplier;

public interface CustomizedSupplierRepository {
    Supplier findSupplierByEmail(String email);
}
