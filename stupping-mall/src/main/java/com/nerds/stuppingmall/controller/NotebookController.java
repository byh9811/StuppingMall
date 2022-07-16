package com.nerds.stuppingmall.controller;

import com.nerds.stuppingmall.domain.Notebook;
import com.nerds.stuppingmall.dto.CategoryInfoRequestDto;
import com.nerds.stuppingmall.dto.NotebookDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nerds.stuppingmall.service.notebook.NotebookDetailsService;
import com.nerds.stuppingmall.service.notebook.NotebookSearchService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NotebookController {
	final NotebookDetailsService notebookDetailsService;
	final NotebookSearchService notebookSearchService;

	@GetMapping("/notebook/{id}")
	public String getProductInfoById(@PathVariable("id") String id, Model model) {
		Notebook notebook = notebookDetailsService.findNotebook(id);

		model.addAttribute("notebook", notebook);
		return "notebookInfo";
	}

	@GetMapping("/notebooks")
	public String getProductsInfoByName(@RequestParam(value = "page", defaultValue = "0") int curPage,
										@RequestParam(value = "order", defaultValue = "최신순") String sortingOrder,
										@RequestParam(value = "name", defaultValue = "") String name, Model model) {
		Page<NotebookDto.ListResponse> notebookPages = notebookSearchService.getNotebookList(curPage, sortingOrder, name);
		model.addAttribute("notebooks", notebookPages.getContent());
		model.addAttribute("curPage", notebookPages.getNumber());
		model.addAttribute("maxPage", notebookPages.getTotalPages());
		return "notebookInfo";
	}
	
	@GetMapping("/categoryResults")
	public String getProductsInfoByCategory(@RequestParam("page") int curPage, @RequestParam("order") String sortingOrder, @RequestParam("category") CategoryInfoRequestDto categoryInfoRequestDto, Model model) {
		Page<NotebookDto.ListResponse> notebookPages = notebookSearchService.findNotebooksByCategory(curPage, sortingOrder, categoryInfoRequestDto);
		model.addAttribute("notebooks", notebookPages.getContent());
		model.addAttribute("curPage", notebookPages.getNumber());
		model.addAttribute("maxPage", notebookPages.getTotalPages());
		return "notebookInfo";
	}
}
