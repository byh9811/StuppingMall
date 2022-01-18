package com.nerds.stuppingmall.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.dto.MemberDto;
import com.nerds.stuppingmall.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	MemberService memberService;
	
	@GetMapping("/members")
	public @ResponseBody String list() {
		List<Member> list = memberService.findAllMember();
		StringBuilder sb = new StringBuilder();
		for(Member m: list)
			sb.append(m.getName()).append("\n");
		
		return sb.toString();
	}
	
	@PostMapping("/deleteUser")
	public String deleteUser(String userId, String password, String checkLetter) {
		if(!checkLetter.equals("sure delete"))
			return "redirect:/";
		memberService.deleteUser(userId, password);
		return "redirect:/members";
	}

	@PostMapping("/findUserId")
	public @ResponseBody String findUserId(String name, String phoneNum) {
		return memberService.findUserId(name, phoneNum);
	}

	@PostMapping("/findPassword")
	public @ResponseBody String findPassword(String userId, String phoneNum) {
		return memberService.findPassword(userId, phoneNum);
	}
	
	@PostMapping("/changePassword")
	public String changePassword(String userId, String newPassword) {
		memberService.updatePassword(userId, newPassword);
		return "redirect:/";
	}
	
	@PostMapping("/addMember")
	public String save(MemberDto memberDto) {
		memberService.insertMember(memberDto);
		return "redirect:/";
	}
}