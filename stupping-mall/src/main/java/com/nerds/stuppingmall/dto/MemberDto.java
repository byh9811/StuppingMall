package com.nerds.stuppingmall.dto;

import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.service.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberDto {
	private String _id;
	private Role role;
	private String userId;
	private String password;
	private String name;
	private String email;
	private String phoneNum;
	private String birth;
	private boolean man;
	
	public Member toDomain() {
		return new Member(_id, role.name(), userId, password, name, email, phoneNum, birth, man);
	}
}
