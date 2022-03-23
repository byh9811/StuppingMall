package com.nerds.stuppingmall.controller;

import com.nerds.stuppingmall.domain.Order;
import com.nerds.stuppingmall.dto.NotebookResponseBasicDto;
import com.nerds.stuppingmall.dto.OrderSalesInfoResponseDto;
import com.nerds.stuppingmall.service.notebook.NotebookSearchService;
import com.nerds.stuppingmall.service.order.OrderSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
public class RestController {
    final NotebookSearchService notebookSearchService;
    final OrderSearchService orderSearchService;

    @GetMapping("notebooks/search")
    public List<NotebookResponseBasicDto> searchNotebooks(String reqModelName) {
        Page<NotebookResponseBasicDto> notebookResponseBasicDtoPage = notebookSearchService.findNotebookBasicDtosByName(0, "최신순", reqModelName);
        return notebookResponseBasicDtoPage.getContent();
    }

    @GetMapping("sales")
    public OrderSalesInfoResponseDto getSalesInfo(String notebookId, String duration) {
        return orderSearchService.findSales(duration, notebookId);
    }
}