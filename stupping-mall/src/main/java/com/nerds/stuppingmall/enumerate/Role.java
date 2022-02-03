package com.nerds.stuppingmall.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
	ADMIN("ROLE_ADMIN", "관리자"), CUSTOMER("ROLE_CUSTOMER", "일반회원"), SUPPLIER("ROLE_SUPPLIER", "기업회원");
	
	private String value;
	private String display;
}
