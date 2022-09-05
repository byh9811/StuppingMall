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

	@GetMapping("/items/new/index")
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

	@GetMapping("/items/{id}/imgs")
	public String getNotebookImgs(@PathVariable("id") String id, Model model) {
		model.addAttribute("imgs", notebookDetailsService.findNotebookImgs(id));
		return "notebookImgs";
	}

	@GetMapping("/items")
	public String getNotebooks(@RequestParam(value = "page", defaultValue = "0") int curPage,
							   @RequestParam(value = "sort", defaultValue = "recent") String sortingOrder,
							   @RequestParam(value = "name", defaultValue = "") String name,
							   @RequestParam("category") CategoryInfoRequestDto categoryInfoRequestDto, Model model) {
		Page<NotebookDto.ListResponse> notebookPages = notebookSearchService.findNotebooks(curPage, sortingOrder, name, categoryInfoRequestDto);
		model.addAttribute("notebooks", notebookPages.getContent());
		model.addAttribute("curPage", notebookPages.getNumber());
		model.addAttribute("maxPage", notebookPages.getTotalPages());
		model.addAttribute("sort", sortingOrder);
		model.addAttribute("category", categoryInfoRequestDto);
		model.addAttribute("searchWord", name);

		return "common/notebooksAll";
	}
}
