package com.nerds.stuppingmall.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;

import com.mongodb.lang.NonNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public abstract class Member {
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
	@Getter
	public static class Address {
		private String base;
		private String detail;
	}
}