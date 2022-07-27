package com.nerds.stuppingmall.dto;

import com.nerds.stuppingmall.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerMyPageResponse {
    private String email;
    private String name;
    private String phoneNum;
    private String baseAddress;
    private String detailAddress;
    private String bank;
    private String accountNumber;
    private Integer balance;

    public CustomerMyPageResponse(Customer customer) {
        this.email = customer.getEmail();
        this.name = customer.getName();
        this.phoneNum = customer.getPhoneNum();
        this.baseAddress = customer.getAddress().getBase();
        this.detailAddress = customer.getAddress().getDetail();
        this.bank = customer.getAccount().getBank();
        this.accountNumber = customer.getAccount().getAccountNumber();
        this.balance = customer.getBalance();
    }
}
