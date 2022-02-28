package com.nerds.stuppingmall.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.service.member.MemberDeregisterService;
import com.nerds.stuppingmall.service.member.MemberDetailsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
	final MemberDeregisterService memberDeregisterService;
	final MemberDetailsService memberDetailsService;
	
	@GetMapping("/allMembers")
	public String allMemberList(Model model) {
		Page<Member> memberPages = memberDetailsService.findAllMembers(0);
		model.addAttribute("members", memberPages.getContent());
		model.addAttribute("curPage", memberPages.getNumber());
		model.addAttribute("maxPage", memberPages.getTotalPages());
		return "memberInfo";
	}

	@PostMapping("/banMember")
	public String banMember(String id) {
		memberDeregisterService.removeMember(id);
		return "redirect:/admin/allMembers";
	}
}