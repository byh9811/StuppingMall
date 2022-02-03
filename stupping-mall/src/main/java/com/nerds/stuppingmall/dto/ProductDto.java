package com.nerds.stuppingmall.dto;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import com.nerds.stuppingmall.domain.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {
	private String name;
	private String category;
	private int price;
	private MultipartFile imgFile;
	
	public ProductDto(Product product) {
		this.name = product.getName();
		this.category = product.getCategory();
		this.price = product.getPrice();
	}
}
