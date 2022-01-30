package com.nerds.stuppingmall.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nerds.stuppingmall.dto.MemberDto;
import com.nerds.stuppingmall.dto.MemberSignupRequestDto;
import com.nerds.stuppingmall.enumerate.Role;
import com.nerds.stuppingmall.model.SingleResult;
import com.nerds.stuppingmall.provider.JwtProvider;
import com.nerds.stuppingmall.service.MemberService;
import com.nerds.stuppingmall.service.ResponseService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class SignController {
	private final MemberService memberService;
	private final JwtProvider jwtProvider;
	private final ResponseService responseService;
	private final BCryptPasswordEncoder passwordEncoder;
	
	@PostMapping("/signUp")
	public SingleResult<String> signUp(MemberSignupRequestDto memberSignupRequestDto) {
		String signUpId = signService.signUp(memberSignupRequestDto);
		return responseService.getSingleResult(signUpId);
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<MemberDto>> findAll() {
		return ResponseEntity.ok(memberService.findAllMember());
	}
}
