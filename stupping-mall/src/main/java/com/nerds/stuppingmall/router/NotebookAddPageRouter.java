package com.nerds.stuppingmall.router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NotebookAddPageRouter {
    @GetMapping("/notebookAddPage")
    public String notebookAddPage() {
        return "notebookAddPage";
    }
}
