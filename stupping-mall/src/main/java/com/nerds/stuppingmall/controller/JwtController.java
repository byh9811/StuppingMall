package com.nerds.stuppingmall.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nerds.stuppingmall.dto.MemberDto;
import com.nerds.stuppingmall.service.JwtService;
import com.nerds.stuppingmall.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class JwtController {
	private final MemberService memberService;
	private final BCryptPasswordEncoder passwordEncoder;
	
	@PostMapping("/signUp")
	public ResponseEntity<String> signUp(MemberDto memberDto) {
		return memberService.findByUserId(memberDto.getUserId()).isPresent()
				? ResponseEntity.badRequest().build()
				: ResponseEntity.ok(JwtService.createToken(memberService.insertMember(memberDto).toAuthRequest()));
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<MemberDto>> findAll() {
		return ResponseEntity.ok(memberService.findAllMember());
	}
}
