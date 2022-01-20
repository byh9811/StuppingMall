package com.nerds.stuppingmall.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.nerds.stuppingmall.service.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Document(collection="members")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Slf4j
public class Member {
	@Id
	private String _id;
	private String role;
	private String userId;
	private String password;
	private String name;
	private String email;
	private String phoneNum;
	private String birth;
	private boolean man;
}
