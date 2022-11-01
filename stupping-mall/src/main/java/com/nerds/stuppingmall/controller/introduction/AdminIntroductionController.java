package com.nerds.stuppingmall.controller.introduction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminIntroductionController {
	@GetMapping("/introductionModifyPage")
	public String introductionModifyPage() {
		// 미완.
		return "introductionModifyPage";
	}
}