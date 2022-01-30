package com.nerds.stuppingmall.dto;

import java.util.Collections;

import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.enumerate.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberSignupRequestDto {
	private String id;
	private String password;
	private String name;
	private String email;
	private String phoneNum;
	private String role;
	private boolean man;
	
	public Member toEntity() {
		return Member.builder()
				.email(email)
				.password(password)
				.name(name)
				.roles(Collections.singletonList(role.equals("CUSTOMER") ?
						Role.CUSTOMER.getValue() :
						Role.SUPPLIER.getValue()))
				.build();
	}
}
