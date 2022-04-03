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
	final ReportSearchService reportSearchService;

	@GetMapping("/allMembers")
	public String allMemberList(Model model) {
		Page<Member> memberPage = memberDetailsService.findAllMembers(0);
		model.addAttribute("members", memberPage.getContent());
		model.addAttribute("curPage", memberPage.getNumber());
		model.addAttribute("maxPage", memberPage.getTotalPages());
		return "memberInfo";
	}

	@PostMapping("/banMember")
	public String banMember(String id) {
		memberDeregisterService.removeMember(id);
		return "redirect:/admin/allMembers";
	}

	@GetMapping("/reports")
	public String reportList(Model model) {
		Page<Report> reportPage = reportSearchService.findAllReports();
		model.addAttribute("reports", reportPage.getContent());
		model.addAttribute("curPage", reportPage.getNumber());
		model.addAttribute("maxPage", reportPage.getTotalPages());
		return "report";
	}
}