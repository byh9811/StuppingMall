package com.nerds.stuppingmall.controller;

import com.nerds.stuppingmall.service.category.CategoryStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
public class CategoryController {
    final CategoryStatusService categoryStatusService;

    @GetMapping("/category/{name}")
    public ResponseEntity<List<String>> getCategory(@PathVariable("name") String category, Model model) {
        List<String> cateList = categoryStatusService.getCategoryDetail(category);
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        return ResponseEntity.ok()
                .headers(headers)
                .body(cateList);
    }
}