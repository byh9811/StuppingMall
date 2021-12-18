package com.nerds.stuppingmall.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="members")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Member {
	private Long id;
	private String password;
	private String name;
	private String email;
	private String phoneNum;
	private Date birth;
	private boolean man;
}
