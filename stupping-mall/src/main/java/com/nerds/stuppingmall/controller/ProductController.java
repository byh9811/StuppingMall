package com.nerds.stuppingmall.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nerds.stuppingmall.dto.ProductDto;
import com.nerds.stuppingmall.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	
	@GetMapping("productInfo")
	public String getProductInfo(String id, Model model) {
		ProductDto product = productService.getProduct(id);
		List<ProductDto> productList = new ArrayList<>();
		productList.add(product);
		
		model.addAttribute("products", productList);
		return "productInfo";
	}
}
