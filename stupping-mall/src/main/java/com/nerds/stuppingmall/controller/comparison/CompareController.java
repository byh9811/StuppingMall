package com.nerds.stuppingmall.controller.comparison;

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

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CompareController {
	final NotebookDetailsService notebookDetailsService;
	final NotebookSearchService notebookSearchService;

	@GetMapping("/comparison/index")
	public String enterComparePage() {
		return "customer/compare";
	}

	@GetMapping("/comparison/items")
	public String searchItems(@RequestParam(value = "page", defaultValue = "0") int curPage,
							  @RequestParam(value = "sort", defaultValue = "recent") String sortingOrder,
							  @RequestParam(value = "name", defaultValue = "") String name, Model model) {
		Page<NotebookDto.CompareInfoResponse> notebookList = notebookSearchService.getCompareInfos(curPage, sortingOrder, name);

		model.addAttribute("items", notebookList);
		return "customer/comparePage";
	}
}
