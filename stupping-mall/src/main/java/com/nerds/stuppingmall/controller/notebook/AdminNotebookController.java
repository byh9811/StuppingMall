package com.nerds.stuppingmall.controller.notebook;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminNotebookController {
    @GetMapping("/notebookAddPage")
    public String notebookAddPage() {
        // 미완.
        return "notebookAddPage";
    }
}