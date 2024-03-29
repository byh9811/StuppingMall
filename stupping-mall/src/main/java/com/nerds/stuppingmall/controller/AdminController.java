package com.nerds.stuppingmall.controller;

import com.nerds.stuppingmall.domain.Introduction;
import com.nerds.stuppingmall.domain.Tip;
import com.nerds.stuppingmall.domain.Report;
import com.nerds.stuppingmall.dto.Authentication;
import com.nerds.stuppingmall.service.introduction.IntroductionModifyService;
import com.nerds.stuppingmall.service.introduction.IntroductionSearchService;
import com.nerds.stuppingmall.service.member.MemberModifyService;
import com.nerds.stuppingmall.service.tip.TipModifyService;
import com.nerds.stuppingmall.service.tip.TipSearchService;
import com.nerds.stuppingmall.service.notebook.NotebookSearchService;
import com.nerds.stuppingmall.service.report.ReportSearchService;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.service.member.MemberDeregisterService;
import com.nerds.stuppingmall.service.member.MemberDetailsService;

import lombok.RequiredArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
	final MemberDeregisterService memberDeregisterService;
	final MemberDetailsService memberDetailsService;
	final MemberModifyService memberModifyService;
	final ReportSearchService reportSearchService;
	final IntroductionModifyService introductionModifyService;
	final TipModifyService tipModifyService;
	final NotebookSearchService notebookSearchService;
	final IntroductionSearchService introductionSearchService;
	final TipSearchService tipSearchService;

	@GetMapping("/main")
	public String adminMain(@AuthenticationPrincipal Authentication authentication, Model model) {
		model.addAttribute("date", LocalTime.now());
		model.addAttribute("recentNotebooks", notebookSearchService.getNew8Notebooks());
		model.addAttribute("introductions", introductionSearchService.getAllIntroductions());

		return "adminMain";
	}

	@GetMapping("/allMembers")
	public String allMemberList(Model model) {
		Page<Member> memberPage = memberDetailsService.findAllMembers(0);
		model.addAttribute("members", memberPage.getContent());
		model.addAttribute("curPage", memberPage.getNumber());
		model.addAttribute("maxPage", memberPage.getTotalPages());
		return "memberInfo";
	}

	@DeleteMapping("/banMember/{id}")
	public String banMember(@PathVariable("id") String id) {
		memberDeregisterService.removeMember(id);
		return "redirect:/admin/allMembers";
	}

	@GetMapping("/reports")
	public String reportList(@RequestParam("page") int curPage, Model model) {
		Page<Report> reportPage = reportSearchService.findAllReports(curPage);
		model.addAttribute("reports", reportPage.getContent());
		model.addAttribute("curPage", reportPage.getNumber());
		model.addAttribute("maxPage", reportPage.getTotalPages());
		return "report";
	}

	@PutMapping("/updateIntroduction")
	public String updateIntroduction(@RequestParam("introductions") List<Introduction> introductions) {
		introductionModifyService.updateIntroduction(introductions);
		return "introductionModifyPage";
	}

	@PutMapping("/updateKnowhow")
	public String updateKnowhow(@RequestParam("tips") List<Tip> tips) {
		tipModifyService.updateKnowhow(tips);
		return "updateKnowhow";
	}

	@PutMapping("/allowSignUp/{id}")
	public String allowSignUp(@PathVariable("id") String email) {
		memberModifyService.updateEnable(email);
		return "allowSignUp";
	}
}