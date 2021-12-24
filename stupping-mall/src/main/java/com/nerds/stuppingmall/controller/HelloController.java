package com.nerds.stuppingmall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.service.MemberService;

@RestController
public class HelloController {
	@Autowired
	MemberService memberService;
	
	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!!";
	}
	
	@GetMapping("/members")
	public String list() {
		List<Member> list = memberService.findAllMember();
		StringBuilder sb = new StringBuilder();
		for(Member m: list)
			sb.append(m.getName()).append("\n");
		
		return sb.toString();
	}
	
	@GetMapping("/addMember")
	public String save() {
		memberService.insertMember();
		
		return "success";
	}
}