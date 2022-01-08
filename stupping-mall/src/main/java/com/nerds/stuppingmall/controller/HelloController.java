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

	@GetMapping("/userDeletePage")
	public String userDeletePage() {
		return "userDeletePage";
	}
	
	@PostMapping("/deleteUser")
	public String deleteUser(String userId, String password, String checkLetter) {
		if(!checkLetter.equals("sure delete"))
			return "redirect:/";
		memberService.deleteUser(userId, password);
		return "redirect:/members";
	}

	@GetMapping("/userIdFindPage")
	public String userIdFindPage() {
		return "userIdFindPage";
	}
	
	@PostMapping("/findUserId")
	public @ResponseBody String findUserId(String name, String phoneNum) {
		return memberService.findUserId(name, phoneNum);
	}

	@GetMapping("/passwordFindPage")
	public String passwordFindPage() {
		return "passwordFindPage";
	}
	
	@PostMapping("/findPassword")
	public @ResponseBody String findPassword(String userId, String phoneNum) {
		return memberService.findPassword(userId, phoneNum);
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