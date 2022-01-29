package com.nerds.stuppingmall.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nerds.stuppingmall.domain.AuthRequest;
import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.dto.MemberDto;
import com.nerds.stuppingmall.error.InputNotFoundException;
import com.nerds.stuppingmall.service.JwtService;
import com.nerds.stuppingmall.service.MemberService;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	public CustomAuthenticationFilter(final AuthenticationManager authenticationManager) {
		super.setAuthenticationManager(authenticationManager);
	}
	
	@Override
	public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response) throws AuthenticationException {
		final UsernamePasswordAuthenticationToken authRequest;
		try {
			final AuthRequest authRq = new ObjectMapper().readValue(request.getInputStream(), AuthRequest.class);
			authRequest = new UsernamePasswordAuthenticationToken(authRq.getUserId(), authRq.getPassword());
		} catch (StreamReadException e) {
			throw new InputNotFoundException();
		} catch (DatabindException e) {
			throw new InputNotFoundException();
		} catch (IOException e) {
			throw new InputNotFoundException();
		}
		setDetails(request, authRequest);
		return this.getAuthenticationManager().authenticate(authRequest);
	}
}
