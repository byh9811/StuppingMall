package com.nerds.stuppingmall.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
	ADMIN("ROLE_ADMIN"), SUPPLIER("ROLE_SUPPLIER"), CUSTOMER("ROLE_CUSTOMER");
	
	private String value;
}