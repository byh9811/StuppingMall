package com.nerds.stuppingmall.dto;

import java.util.ArrayList;

import com.nerds.stuppingmall.domain.Account;
import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.enumerate.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MemberSignUpRequestDto {
	@NotNull
	private Role role;

	@NotNull
	@Pattern(regexp = "^[a-zA-Z0-9]{6,12}$")
	private String id;

	@NotNull
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,20}$")
	private String password;

	@NotNull
	@Pattern(regexp = "^[가-힣]{2,10}$")
	private String name;

	@NotNull
	@Size(max=30)
	@Pattern(regexp = "\\w+@\\w+\\.\\w+(\\.\\w+)?")
	private String email;

	@Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$")
	private String phoneNum;

	private String birth;

	private boolean man;

	private String bank;

	@Pattern(regexp = "^[0-9-]{8,15}$")
	private String accountNumber;
	
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
				.account(new Account(bank, accountNumber))
				.myPicks(new ArrayList<>())
				.build();
	}
}
