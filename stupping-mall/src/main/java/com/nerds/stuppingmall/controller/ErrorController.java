package com.nerds.stuppingmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
	@GetMapping("/forbidden")
	public String forbidden() {
		return "redirect:/html/error403.html";
	}
}