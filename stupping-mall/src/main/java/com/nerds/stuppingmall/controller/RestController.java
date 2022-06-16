package com.nerds.stuppingmall.controller;

import com.nerds.stuppingmall.dto.NotebookResponseBasicDto;
import com.nerds.stuppingmall.dto.OrderSalesInfoResponseDto;
import com.nerds.stuppingmall.service.email.EmailSendService;
import com.nerds.stuppingmall.service.notebook.NotebookSearchService;
import com.nerds.stuppingmall.service.order.OrderSearchService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
public class RestController {
    final NotebookSearchService notebookSearchService;
    final OrderSearchService orderSearchService;
    final EmailSendService emailSendService;

    // 판매자가 그래프에서 노트북검색하는 용인듯? 추후 수정필요
    @GetMapping("notebooks/search")
    public List<NotebookResponseBasicDto> searchNotebooks(String reqModelName) {
        Page<NotebookResponseBasicDto> notebookResponseBasicDtoPage = notebookSearchService.findNotebookBasicDtosByName(0, "최신순", reqModelName);
        return notebookResponseBasicDtoPage.getContent();
    }

    @GetMapping("sales/{id}")
    public OrderSalesInfoResponseDto getSalesInfo(@PathVariable("id") String notebookId, @RequestParam("duration") String duration) {
        return orderSearchService.findSales(duration, notebookId);
    }

    @PostMapping("/auth/email")
    public @ResponseBody String authenticateEmail(HttpServletRequest request, @RequestParam("email") String email) {
        try {
            String key = emailSendService.sendMessage(email);
            HttpSession session = request.getSession();
            session.setAttribute(email, key);
            session.setMaxInactiveInterval(20*60);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Success";
    }

    @GetMapping("/display")
    public @ResponseBody ResponseEntity<byte[]> getImage(@RequestParam("name") String name) {
        byte[] imageByteArray = null;
        try {
            String fileName = "C:\\img\\" + name; // 파일경로
            InputStream imageStream = new FileInputStream(fileName);
            imageByteArray = IOUtils.toByteArray(imageStream);
            imageStream.close();
        } catch (FileNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INSUFFICIENT_STORAGE);
        }

        return new ResponseEntity<>(imageByteArray, HttpStatus.OK);
    }
}