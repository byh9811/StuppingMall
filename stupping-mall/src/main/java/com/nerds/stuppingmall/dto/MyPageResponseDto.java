package com.nerds.stuppingmall.dto;

import com.nerds.stuppingmall.domain.Account;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MyPageResponseDto {
    private String id;
    private String name;
    private String email;
    private String phoneNum;
    private String birth;
    private boolean man;
    private Account account;
    private int balance;
}
