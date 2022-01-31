package com.nerds.stuppingmall.dto;

import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.enumerate.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MemberSignUpRequestDto {
	private Role role;
	private String id;
	private String password;
	private String name;
	private String email;
	private String phoneNum;
	private String birth;
	private boolean man;
	
	public Member toDomain() {
		return Member.builder()
				._id(id)
				.password(password)
				.name(name)
				.email(email)
				.phoneNum(phoneNum)
				.birth(birth)
				.man(man)
				.role(role.getValue())
				.build();
	}
}
