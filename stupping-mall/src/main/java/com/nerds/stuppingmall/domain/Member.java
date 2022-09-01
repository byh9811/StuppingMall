package com.nerds.stuppingmall.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;

import com.mongodb.lang.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("members")
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
	private int balance;
	private String phoneNum;
	private boolean enable;
}