package com.nerds.stuppingmall.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class CustomerMyPageModifyRequest {
    @NotNull
    @Pattern(regexp = "^[가-힣]{2,10}$")
    private String name;

    @Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$")
    private String phoneNum;

    private String baseAddress;
    private String detailAddress;
    private String bank;
    private String accountNumber;
}
