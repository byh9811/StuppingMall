package com.nerds.stuppingmall.domain;


import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
	private String id;
	private String password;
	private String name;
	private String email;
	private String phoneNum;
	private String birth;
	private boolean man;
}
