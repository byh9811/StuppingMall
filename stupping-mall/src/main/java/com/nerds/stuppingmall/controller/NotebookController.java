package com.nerds.stuppingmall.controller;

import java.util.ArrayList;
import java.util.List;

import com.nerds.stuppingmall.domain.Notebook;
import com.nerds.stuppingmall.dto.CategoryInfoRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nerds.stuppingmall.dto.CategoryInfoRequestDto;
import com.nerds.stuppingmall.dto.NotebookListResponseDto;
import com.nerds.stuppingmall.service.notebook.NotebookDetailsService;
import com.nerds.stuppingmall.service.notebook.NotebookSearchService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class NotebookController {
	final NotebookDetailsService notebookDetailsService;
	final NotebookSearchService notebookSearchService;
	
	@GetMapping("/notebookInfo/{id}")
	public String getProductInfoById(@PathVariable("id") String id, Model model) {
		Notebook notebook = notebookDetailsService.findNotebook(id);

		model.addAttribute("notebook", notebook);
		return "notebookInfo";
	}

	@GetMapping("/notebooksInfo")
	public String getProductsInfoByName(@RequestParam("page") int curPage, @RequestParam("order") String sortingOrder, @RequestParam("keyword") String keyword, Model model) {
		Page<NotebookListResponseDto> notebookPages = notebookSearchService.findNotebooksByName(curPage, sortingOrder, keyword);
		model.addAttribute("notebooks", notebookPages.getContent());
		model.addAttribute("curPage", notebookPages.getNumber());
		model.addAttribute("maxPage", notebookPages.getTotalPages());
		return "notebookInfo";
	}
	
	@GetMapping("/categoryResults")
	public String getProductsInfoByCategory(@RequestParam("page") int curPage, @RequestParam("order") String sortingOrder, @RequestParam("category") CategoryInfoRequestDto categoryInfoRequestDto, Model model) {
		Page<NotebookListResponseDto> notebookPages = notebookSearchService.findNotebooksByCategory(curPage, sortingOrder, categoryInfoRequestDto);
		model.addAttribute("notebooks", notebookPages.getContent());
		model.addAttribute("curPage", notebookPages.getNumber());
		model.addAttribute("maxPage", notebookPages.getTotalPages());
		return "notebookInfo";
	}
}
