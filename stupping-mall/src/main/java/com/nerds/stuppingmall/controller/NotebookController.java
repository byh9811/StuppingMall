package com.nerds.stuppingmall.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nerds.stuppingmall.dto.NotebookInfoResponseDto;
import com.nerds.stuppingmall.service.NotebookService;

@Controller
public class NotebookController {
	@Autowired
	NotebookService notebookService;
	
	@GetMapping("notebookInfo")
	public String getProductInfoById(String id, Model model) {
		NotebookInfoResponseDto notebook = notebookService.getNotebook(id);
		List<NotebookInfoResponseDto> notebookList = new ArrayList<>();
		notebookList.add(notebook);
		
		model.addAttribute("notebooks", notebookList);
		return "notebookInfo";
	}

	@GetMapping("notebooksInfo")
	public String getProductsInfoByName(String name, Model model) {
		List<NotebookInfoResponseDto> notebooks = notebookService.getNotebooks(name);
		model.addAttribute("notebooks", notebooks);
		return "notebookInfo";
	}
	
}
