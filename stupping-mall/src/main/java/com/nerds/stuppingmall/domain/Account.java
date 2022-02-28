package com.nerds.stuppingmall.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account {
	private String bank;
	private String accountNumber;
}
