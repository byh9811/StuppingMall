package com.nerds.stuppingmall.controller;

import com.nerds.stuppingmall.domain.Order;
import com.nerds.stuppingmall.dto.NotebookResponseBasicDto;
import com.nerds.stuppingmall.dto.OrderSalesInfoResponseDto;
import com.nerds.stuppingmall.service.email.EmailSendService;
import com.nerds.stuppingmall.service.notebook.NotebookSearchService;
import com.nerds.stuppingmall.service.order.OrderSearchService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
public class RestController {
    final NotebookSearchService notebookSearchService;
    final OrderSearchService orderSearchService;
    final EmailSendService emailSendService;

    @GetMapping("notebooks/search")
    public List<NotebookResponseBasicDto> searchNotebooks(String reqModelName) {
        Page<NotebookResponseBasicDto> notebookResponseBasicDtoPage = notebookSearchService.findNotebookBasicDtosByName(0, "최신순", reqModelName);
        return notebookResponseBasicDtoPage.getContent();
    }

    @GetMapping("sales")
    public OrderSalesInfoResponseDto getSalesInfo(String notebookId, String duration) {
        return orderSearchService.findSales(duration, notebookId);
    }

    @PostMapping("setKey")
    public @ResponseBody String authenticateEmail(HttpServletRequest request, String email) {
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
    public @ResponseBody ResponseEntity<byte[]> getImage(String name) {
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