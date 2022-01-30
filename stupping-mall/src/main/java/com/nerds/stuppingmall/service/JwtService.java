package com.nerds.stuppingmall.service;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.domain.AuthRequest;
import com.nerds.stuppingmall.dto.MemberDto;
import com.nerds.stuppingmall.enumerate.Role;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {
	private static final String secretKey = "StuppingMallSecretKey";
	
	public static String createToken(AuthRequest authRequest) {
		return Jwts.builder()
				.setClaims(createClaims(authRequest))
				.setSubject(authRequest.getUserId())
				.setHeader(createHeader())
				.setExpiration(createExpireDate())
				.signWith(SignatureAlgorithm.HS256, secretKey).compact();
	}
	
	public static boolean isValid(String token) {
		try {
			Claims claims = getClaimsFromToken(token);
			return true;
		} catch (ExpiredJwtException e) {
			return false;
		} catch (JwtException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
	}
	
	private static Date createExpireDate() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 30);
		return c.getTime();
	}
	
	private static Map<String, Object> createHeader() {
		Map<String, Object> header = new HashMap<>();
		header.put("typ", "JWT");
		header.put("alg", "HS256");
		header.put("regDate", System.currentTimeMillis());
		return header;
	}
	
	private static Map<String, Object> createClaims(AuthRequest authRequest) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("id", authRequest.getUserId());
		claims.put("role", authRequest.getRole());
		return claims;
	}
	
	public static String getTokenFromHeader(String header) {
		return header.split(" ")[1];
	}
	
	private static Claims getClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secretKey)
				.parseClaimsJws(token).getBody();
	}
	
	private static String getUserIdFromToken(String token) {
		Claims claims = getClaimsFromToken(token);
		return (String)claims.get("id");
	}
	
	private static Role getRoleFromToken(String token) {
		Claims claims = getClaimsFromToken(token);
		return (Role)claims.get("role");
	}
}