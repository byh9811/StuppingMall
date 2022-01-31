package com.nerds.stuppingmall.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="members")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Member {
	@Id
	private String _id;
	private String password;
	@NonNull
	private String name;
	@NonNull
	private String role;
	private String email;
	private String phoneNum;
	private String birth;
	private boolean man;
}
