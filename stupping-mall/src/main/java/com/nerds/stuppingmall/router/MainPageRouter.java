package com.nerds.stuppingmall.router;

import com.nerds.stuppingmall.dto.Authentication;
import com.nerds.stuppingmall.service.category.CategoryStatusService;
import com.nerds.stuppingmall.service.introduction.IntroductionSearchService;
import com.nerds.stuppingmall.service.notebook.NotebookSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.integration.IntegrationDataSourceScriptDatabaseInitializer;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalTime;

@Controller
@RequiredArgsConstructor
public class MainPageRouter {
    final NotebookSearchService notebookSearchService;
    final CategoryStatusService categoryStatusService;
    final IntroductionSearchService introductionSearchService;

    @GetMapping("/")
    public String main(@AuthenticationPrincipal Authentication authentication) {
        String redirectURI;

        if(authentication==null)
            redirectURI = "/main";
        else if(authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")))
            redirectURI = "/admin/main";
        else
            redirectURI = "/main";

        return "redirect:"+redirectURI;
    }

    @GetMapping("/main")
    public String customerMain(Model model) {
        model.addAttribute("date", LocalTime.now());
        model.addAttribute("topNotebooks", notebookSearchService.getTop3Notebooks());
        model.addAttribute("categories", categoryStatusService.getCategories());
        model.addAttribute("introductions", introductionSearchService.getAllIntroductions());

        return "/common/main";
    }

}
