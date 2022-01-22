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

import com.nerds.stuppingmall.dto.MemberDto;
import com.nerds.stuppingmall.dto.ProductDetailDto;
import com.nerds.stuppingmall.dto.ProductDto;
import com.nerds.stuppingmall.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	
	@GetMapping("productInfo")
	public String getProductInfo(String id, Model model) {
		ProductDetailDto product = productService.getProduct(id);
		List<ProductDetailDto> productList = new ArrayList<>();
		productList.add(product);
		
		model.addAttribute("products", productList);
		return "productInfo";
	}
	
	@GetMapping("productsInfo")
	public String getProductsInfo(String name, Model model) {
		List<ProductDetailDto> products = productService.getProducts(name);
		model.addAttribute("products", products);
		return "productInfo";
	}
	
	@PostMapping("/seller/addProduct")
	public String addProduct(@AuthenticationPrincipal MemberDto memberDto, ProductDto productDto) {
		String id = productService.addProduct(memberDto.getName(), productDto);
		return "redirect:/productInfo?id=" + id;
	}
	
	@GetMapping("/seller/myProducts")
	public String myProducts(@AuthenticationPrincipal MemberDto memberDto, Model model) {
		List<ProductDetailDto> products = productService.getMyProducts(memberDto);
		model.addAttribute("products", products);
		
		return "productInfo";
	}
	
	@PostMapping("/seller/deleteProduct")
	public String deleteProduct(@AuthenticationPrincipal MemberDto memberDto, String id) {
		productService.deleteProduct(memberDto, id);
		
		return "redirect:/seller/myProducts";
	}
}
