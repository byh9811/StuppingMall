package com.nerds.stuppingmall.dto;

import com.nerds.stuppingmall.domain.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberDto {
	private String _id;
	private String userId;
	private String password;
	private String name;
	private String email;
	private String phoneNum;
	private String birth;
	private boolean man;
	
	public Member toDomain() {
		return new Member(_id, userId, password, name, email, phoneNum, birth, man);
	}
}
