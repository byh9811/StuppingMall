package com.nerds.stuppingmall.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nerds.stuppingmall.dto.NotebookInfoRequestDto;
import com.nerds.stuppingmall.dto.NotebookInfoResponseDto;
import com.nerds.stuppingmall.service.notebook.NotebookDetailsService;
import com.nerds.stuppingmall.service.notebook.NotebookSearchService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NotebookController {
	final NotebookDetailsService notebookDetailsService;
	final NotebookSearchService notebookSearchService;
	final int SIZE_PER_PAGE = 10;
	
	@GetMapping("/notebookInfo")
	public String getProductInfoById(String id, Model model) {
		NotebookInfoResponseDto notebook = notebookDetailsService.findNotebook(id);
		// 단일 검색 페이지 만들면 단일값 리턴으로 고칠것
		List<NotebookInfoResponseDto> notebookList = new ArrayList<>();
		notebookList.add(notebook);
		
		model.addAttribute("notebooks", notebookList);
		return "notebookInfo";
	}

	@GetMapping("/notebooksInfo")
	public String getProductsInfoByName(int curPage, String sortingOrder, String name, Model model) {
		Page<NotebookInfoResponseDto> notebookPages = notebookSearchService.findNotebooksByName(curPage, sortingOrder, name);
		model.addAttribute("notebooks", notebookPages.getContent());
		model.addAttribute("curPage", notebookPages.getNumber());
		model.addAttribute("maxPage", notebookPages.getTotalPages());
		return "notebookInfo";
	}
	
	@GetMapping("/notebooksInfo/category")
	public String getProductsInfoByCategory(int curPage, String sortingOrder, NotebookInfoRequestDto notebookInfoRequestDto, Model model) {
		Page<NotebookInfoResponseDto> notebookPages = notebookSearchService.findNotebooksByCategory(curPage, sortingOrder, notebookInfoRequestDto);
		model.addAttribute("notebooks", notebookPages.getContent());
		model.addAttribute("curPage", notebookPages.getNumber());
		model.addAttribute("maxPage", notebookPages.getTotalPages());
		return "notebookInfo";
	}
	
	
}
