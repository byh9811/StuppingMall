package com.nerds.stuppingmall.router;

import com.nerds.stuppingmall.service.notebook.NotebookSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalTime;

@Controller
@RequiredArgsConstructor
public class Top8PageRouter {
    final NotebookSearchService notebookSearchService;

    @GetMapping("/top8Page")
    public String introductionModifyPage(Model model) {
        model.addAttribute("date", LocalTime.now());
        model.addAttribute("recentNotebooks", notebookSearchService.getRecent9Notebooks());

        return "common/top8";
    }
}
