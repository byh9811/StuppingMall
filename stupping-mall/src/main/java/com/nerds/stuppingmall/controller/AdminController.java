package com.nerds.stuppingmall.controller;

import com.nerds.stuppingmall.domain.Introduction;
import com.nerds.stuppingmall.domain.Knowhow;
import com.nerds.stuppingmall.domain.Report;
import com.nerds.stuppingmall.dto.Authentication;
import com.nerds.stuppingmall.service.introduction.IntroductionModifyService;
import com.nerds.stuppingmall.service.introduction.IntroductionSearchService;
import com.nerds.stuppingmall.service.knowhow.KnowhowModifyService;
import com.nerds.stuppingmall.service.knowhow.KnowhowSearchService;
import com.nerds.stuppingmall.service.notebook.NotebookSearchService;
import com.nerds.stuppingmall.service.report.ReportSearchService;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	final ReportSearchService reportSearchService;
	final IntroductionModifyService introductionModifyService;
	final KnowhowModifyService knowhowModifyService;
	final NotebookSearchService notebookSearchService;
	final IntroductionSearchService introductionSearchService;
	final KnowhowSearchService knowhowSearchService;

	@GetMapping("/main")
	public String adminMain(@AuthenticationPrincipal Authentication authentication, Model model) {
		model.addAttribute("date", LocalTime.now());
		model.addAttribute("recentNotebooks", notebookSearchService.getRecent8Notebooks());
		model.addAttribute("introductions", introductionSearchService.getAllIntroductions());
		model.addAttribute("knowhows", knowhowSearchService.getAllKnowhows());

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

	@PostMapping("/banMember")
	public String banMember(String id) {
		memberDeregisterService.removeMember(id);
		return "redirect:/admin/allMembers";
	}

	@GetMapping("/reports")
	public String reportList(Model model, int curPage) {
		Page<Report> reportPage = reportSearchService.findAllReports(curPage);
		model.addAttribute("reports", reportPage.getContent());
		model.addAttribute("curPage", reportPage.getNumber());
		model.addAttribute("maxPage", reportPage.getTotalPages());
		return "report";
	}

	@PostMapping("/updateIntroduction")
	public String updateIntroduction(List<Introduction> introductions) {
		introductionModifyService.updateIntroduction(introductions);
		return "introductionModifyPage";
	}

	@PostMapping("/updateKnowhow")
	public String updateKnowhow(List<Knowhow> knowhows) {
		knowhowModifyService.updateKnowhow(knowhows);
		return "updateKnowhow";
	}
}