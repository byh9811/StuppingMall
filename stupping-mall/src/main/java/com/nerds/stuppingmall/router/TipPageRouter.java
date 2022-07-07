package com.nerds.stuppingmall.router;

import com.nerds.stuppingmall.service.notebook.NotebookSearchService;
import com.nerds.stuppingmall.service.tip.TipSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalTime;

@Controller
@RequiredArgsConstructor
public class TipPageRouter {
    final TipSearchService tipSearchService;

    @GetMapping("/tipPage")
    public String tipPage(Model model) {
        model.addAttribute("date", LocalTime.now());
        model.addAttribute("tips", tipSearchService.getAllTips());

        return "common/new8";
    }
}
