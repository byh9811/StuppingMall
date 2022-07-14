package com.nerds.stuppingmall.router;

import com.nerds.stuppingmall.service.notebook.NotebookSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class NotebookListPage {
    private final NotebookSearchService notebookSearchService;

    @GetMapping("/notebookAllPage")
    public String getNotebookAllPage(Model model) {
        model.addAttribute("notebooks", notebookSearchService.getNotebooksTemp());
        model.addAttribute("curPage", 0);
        model.addAttribute("maxPage", 10);

        return "common/notebooksAll";
    }
}
