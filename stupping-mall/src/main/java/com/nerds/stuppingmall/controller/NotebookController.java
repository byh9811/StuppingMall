package com.nerds.stuppingmall.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nerds.stuppingmall.dto.NotebookInfoRequestDto;
import com.nerds.stuppingmall.dto.NotebookInfoResponseDto;
import com.nerds.stuppingmall.service.NotebookService;

@Controller
public class NotebookController {
	@Autowired
	NotebookService notebookService;
	
	@GetMapping("/notebookInfo")
	public String getProductInfoById(String id, Model model) {
		NotebookInfoResponseDto notebook = notebookService.getNotebook(id);
		// 단일 검색 페이지 만들면 단일값 리턴으로 고칠것
		List<NotebookInfoResponseDto> notebookList = new ArrayList<>();
		notebookList.add(notebook);
		
		model.addAttribute("notebooks", notebookList);
		return "notebookInfo";
	}

	@GetMapping("/notebooksInfo")
	public String getProductsInfoByName(String name, Model model) {
		List<NotebookInfoResponseDto> notebooks = notebookService.getNotebooks(name);
		model.addAttribute("notebooks", notebooks);
		return "notebookInfo";
	}
	
	@GetMapping("/notebooksInfo/category")
	public String getProductsInfoByCategory(NotebookInfoRequestDto notebookInfoRequestDto, Model model) {
		List<NotebookInfoResponseDto> notebooks = notebookService.categorySearchTest(1, notebookInfoRequestDto);
		model.addAttribute("notebooks", notebooks);
		return "notebookInfo";
	}
	
	
}
