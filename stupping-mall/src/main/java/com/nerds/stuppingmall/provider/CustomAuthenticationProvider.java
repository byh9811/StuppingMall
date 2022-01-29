package com.nerds.stuppingmall.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.nerds.stuppingmall.dto.MemberDto;
import com.nerds.stuppingmall.service.AuthenticationService;
import com.nerds.stuppingmall.service.MemberService;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
	private final AuthenticationService authenticationService;
	private final BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		final UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
		final String id = token.getName();
		final String pw = (String)token.getCredentials();
		final MemberDto memberDto = authenticationService.loadUserByUsername(id);
		
		if(!passwordEncoder.matches(pw, memberDto.getPassword())) {
			throw new RuntimeException(memberDto.getUserId() + "Invalid password");
		}
		
		return new UsernamePasswordAuthenticationToken(memberDto, pw, memberDto.getAuthorities());
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
