package com.nerds.stuppingmall.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.nerds.stuppingmall.service.JwtService;


public class JwtTokenInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws IOException {
		final String header = request.getHeader("Authorization");
		
		if(header != null) {
			final String token = JwtService.getTokenFromHeader(header);
			if(JwtService.isValid(token)) {
				return true;
			}
		}
		
		response.sendRedirect("error/forbidden");
		return false;
	}
}
