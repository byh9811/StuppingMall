package com.nerds.stuppingmall.router;

import com.nerds.stuppingmall.service.notebook.NotebookSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalTime;

@Controller
@RequiredArgsConstructor
public class New8PageRouter {
    final NotebookSearchService notebookSearchService;

    @GetMapping("/new8Page")
    public String introductionModifyPage(Model model) {
        model.addAttribute("date", LocalTime.now());
        model.addAttribute("newNotebooks", notebookSearchService.getNew8Notebooks());

        return "common/new8";
    }
}
