package com.nerds.stuppingmall.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.dto.MemberSignUpRequestDto;
import com.nerds.stuppingmall.service.MemberService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	MemberService memberService;
	
	@GetMapping("/allMembers")
	public String list(Model model) {
		List<Member> members = memberService.findAllMember();
		model.addAttribute("members", members);
		return "memberInfo";
	}

	@PostMapping("/banMember")
	public String banMember(String id) {
		memberService.banMember(id);
		return "redirect:/admin/allMembers";
	}

}