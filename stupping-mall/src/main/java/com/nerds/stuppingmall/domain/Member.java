package com.nerds.stuppingmall.domain;

import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;

import com.mongodb.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class Member {
	@Id
	private String email;
	@NonNull
	private String password;
	@NonNull
	private String name;
	@NonNull
	private String role;
	private Account account;
	private Integer balance;
	private Address address;

	@AllArgsConstructor
	public static class Address {
		private String base;
		private String detail;
	}
}