package com.nerds.stuppingmall.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.nerds.stuppingmall.domain.AuthRequest;
import com.nerds.stuppingmall.dto.MemberDto;
import com.nerds.stuppingmall.service.JwtService;

public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	@Override
	public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) {
		final MemberDto memberDto = ((MemberDto)authentication.getPrincipal());
		final AuthRequest authRequest = new AuthRequest(memberDto.getUserId(), memberDto.getPassword(), memberDto.getRole());
		final String token = JwtService.createToken(authRequest);
		response.addHeader("Authorization", "BEARER" + " " + token);
	}
}
