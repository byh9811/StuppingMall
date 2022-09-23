package com.nerds.stuppingmall.controller;

import com.nerds.stuppingmall.dto.Authentication;
import com.nerds.stuppingmall.service.category.CategoryStatusService;
import com.nerds.stuppingmall.service.introduction.IntroductionSearchService;
import com.nerds.stuppingmall.service.notebook.NotebookSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalTime;

@Controller
@RequiredArgsConstructor
public class HomeController {
    final NotebookSearchService notebookSearchService;
    final CategoryStatusService categoryStatusService;
    final IntroductionSearchService introductionSearchService;

    @GetMapping("/")
    public String main(@AuthenticationPrincipal Authentication authentication) {
        String redirectURI;

        if(authentication!=null && authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")))
            redirectURI = "/admin/index";
        else
            redirectURI = "/index";

        return "redirect:"+redirectURI;
    }

    @GetMapping("/index")
    public String main(Model model) {
        model.addAttribute("date", LocalTime.now());
        model.addAttribute("topNotebooks", notebookSearchService.getTop3Notebooks());
        model.addAttribute("categories", categoryStatusService.getCategories());
        model.addAttribute("introductions", introductionSearchService.getAllIntroductions());

        return "/common/main";
    }
    
    @GetMapping("/admin/index")
    public String adminMain(Model model) {
        // 미완

        return "/common/main";
    }
}
