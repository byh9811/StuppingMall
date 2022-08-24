package com.nerds.stuppingmall.controller.notebook;

import com.nerds.stuppingmall.domain.Notebook;
import com.nerds.stuppingmall.dto.CategoryInfoRequestDto;
import com.nerds.stuppingmall.dto.NotebookDto;
import com.nerds.stuppingmall.service.notebook.NotebookDetailsService;
import com.nerds.stuppingmall.service.notebook.NotebookSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalTime;

@Controller
@RequiredArgsConstructor
public class NotebookController {
	final NotebookDetailsService notebookDetailsService;
	final NotebookSearchService notebookSearchService;

	@GetMapping("/items")
	public String getNotebooks(Model model) {
		// 미완. 전체 노트북 검색 페이지를 하나로 합치면서 로직이 달라짐.

		model.addAttribute("notebooks", notebookSearchService.getNotebookList(0, "최신순", ""));
		model.addAttribute("curPage", 0);
		model.addAttribute("maxPage", 10);

		return "common/notebooksAll";
	}

	@GetMapping("/new8Page")
	public String enterNew8Page(Model model) {
		model.addAttribute("date", LocalTime.now());
		model.addAttribute("newNotebooks", notebookSearchService.getNew8Notebooks());

		return "common/new8";
	}

	@GetMapping("/items/{id}")
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
