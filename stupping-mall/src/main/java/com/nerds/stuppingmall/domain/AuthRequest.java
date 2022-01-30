package com.nerds.stuppingmall.domain;

import com.nerds.stuppingmall.enumerate.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
	private String userId;
	private String password;
	private Role role;
}
