package com.nerds.stuppingmall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.service.MemberService;

@Controller
public class HelloController {
	@Autowired
	MemberService memberService;
	
	@GetMapping("/")
	public String index() {
		return "main";
	}
	
	@GetMapping("/members")
	public @ResponseBody String list() {
		List<Member> list = memberService.findAllMember();
		StringBuilder sb = new StringBuilder();
		for(Member m: list)
			sb.append(m.getName()).append("\n");
		
		return sb.toString();
	}
	
	@GetMapping("/passwordChangePage")
	public String passwordChangePage() {
		return "passwordChangePage";
	}
	
	@PostMapping("/changePassword")
	public String changePassword(String userId, String newPassword) {
		memberService.updatePassword(userId, newPassword);
		return "redirect:/";
	}
	
	@GetMapping("/memberAddPage")
	public String memberAddPage() {
		return "memberAddPage";
	}
	
	@PostMapping("/addMember")
	public String save(Member m) {
		memberService.insertMember(m);
		
		return "redirect:/";
	}
}