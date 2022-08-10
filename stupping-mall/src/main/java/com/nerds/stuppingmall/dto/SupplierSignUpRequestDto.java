package com.nerds.stuppingmall.dto;

import com.nerds.stuppingmall.domain.Account;
import com.nerds.stuppingmall.domain.Supplier;
import com.nerds.stuppingmall.enumerate.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SupplierSignUpRequestDto {
    @NotNull
    private Role role;

    @NotNull
    @Size(max=30)
    @Pattern(regexp = "\\w+@\\w+\\.\\w+(\\.\\w+)?")
    private String email;

    @NotNull
    private String authCode;

    @NotNull
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,20}$")
    private String password;

    @NotNull
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,20}$")
    private String passwordCheck;

    @NotNull
    @Pattern(regexp = "^[가-힣]{2,10}$")
    private String name;

    @Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$")
    private String phoneNum;

    private String bank;

    @Pattern(regexp = "^[0-9-]{8,15}$")
    private String accountNumber;

    private String companyRegistrationNumber;

    private String contactPerson;

    public Supplier toDomain() {
        return Supplier.builder()
                .email(email)
                .password(password)
                .name(name)
                .phoneNum(phoneNum)
                .role(role.getValue())
                .account(new Account(bank, accountNumber))
                .balance(0)
                .build();
    }
}
