package com.nerds.stuppingmall.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.nerds.stuppingmall.domain.Member;

import lombok.Getter;

@Getter
public class MemberLoginResponseDto {
	private final String id;
	private final List<String> roles;
	
	public MemberLoginResponseDto(Member member) {
		this.id = member.get_id();
		this.roles = member.getRoles();
	}
}
