package com.nerds.stuppingmall.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
	ADMIN("ROLE_ADMIN", "관리자"), MEMBER("ROLE_MEMBER", "일반회원"), SELLER("ROLE_SELLER", "기업회원");
	
	private String value;
	private String display;
}
