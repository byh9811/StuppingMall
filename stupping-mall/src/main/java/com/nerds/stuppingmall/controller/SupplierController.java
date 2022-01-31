package com.nerds.stuppingmall.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.domain.Product;
import com.nerds.stuppingmall.dto.Authentication;
import com.nerds.stuppingmall.dto.MemberSignUpRequestDto;
import com.nerds.stuppingmall.dto.ProductAddRequestDto;
import com.nerds.stuppingmall.dto.ProductDto;
import com.nerds.stuppingmall.service.MemberService;
import com.nerds.stuppingmall.service.ProductService;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
	@Autowired
	MemberService memberService;
	@Autowired
	ProductService productService;
	
	@PostMapping("/addProduct")
	public String addProduct(@AuthenticationPrincipal Authentication authentication, ProductAddRequestDto productAddRequestDto) {
		String id = productService.addProduct(authentication.getId(), productAddRequestDto);
		return "redirect:/productInfo?id=" + id;
	}
	
	@GetMapping("/myProducts")
	public String myProducts(@AuthenticationPrincipal Authentication authentication, Model model) {
		List<Product> products = productService.getMyProducts(authentication.getId());
		model.addAttribute("products", products);
		
		return "productInfo";
	}
	
	@PostMapping("/deleteProduct")
	public String deleteProduct(@AuthenticationPrincipal Authentication authentication, String productId) {
		productService.deleteProduct(authentication.getId(), productId);
		
		return "redirect:/myProducts";
	}
}