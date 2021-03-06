package com.nerds.stuppingmall.controller;

import com.nerds.stuppingmall.dto.NotebookDto;
import com.nerds.stuppingmall.dto.OrderSalesInfoResponseDto;
import com.nerds.stuppingmall.service.email.EmailSendService;
import com.nerds.stuppingmall.service.notebook.NotebookSearchService;
import com.nerds.stuppingmall.service.order.OrderSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
public class RestController {
    final NotebookSearchService notebookSearchService;
    final OrderSearchService orderSearchService;
    final EmailSendService emailSendService;

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
    public @ResponseBody ResponseEntity<byte[]> getImage(@RequestParam("name") String name, @RequestParam("w") int width, @RequestParam("h") int height) {
        byte[] imageByteArray = null;
        try {
            String fileName = "C:\\img\\" + name; // 파일경로
            File file = new File(fileName);
            BufferedImage image = ImageIO.read(file);
            Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            BufferedImage imgBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            imgBuff.getGraphics().drawImage(resizedImage, 0, 0, new Color(0, 0, 0), null);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(imgBuff, "jpg", baos);
            imageByteArray = baos.toByteArray();
//            InputStream imageStream = new FileInputStream(fileName);
//            imageByteArray = IOUtils.toByteArray(imageStream);
//            imageStream.close();
        } catch (FileNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INSUFFICIENT_STORAGE);
        }

        return new ResponseEntity<>(imageByteArray, HttpStatus.OK);
    }
}