package com.nerds.stuppingmall.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.nerds.stuppingmall.domain.Product;
import com.nerds.stuppingmall.dto.ProductDto;
import com.nerds.stuppingmall.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	
	@GetMapping("productInfo")
	public String getProductInfoById(String id, Model model) {
		Product product = productService.getProduct(id);
		List<Product> productList = new ArrayList<>();
		productList.add(product);
		
		model.addAttribute("products", productList);
		return "productInfo";
	}

	@GetMapping("productsInfo")
	public String getProductsInfoByName(String name, Model model) {
		List<Product> products = productService.getProducts(name);
		model.addAttribute("products", products);
		return "productInfo";
	}
	
}
