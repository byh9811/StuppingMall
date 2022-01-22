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
public class ProductDetailDto extends ProductDto {
	@Id
	private String _id;
	private String seller;
	private String img;
	private int view;
	private double rate;
	private int salesVolume;
	private String date;
	
	public ProductDetailDto(Product product) {
		super(product);
		this._id = product.get_id();
		this.seller = product.getSeller();
		this.img = product.getImg();
		this.view = product.getView();
		this.rate = product.getRate();
		this.salesVolume = product.getSalesVolume();
		this.date = product.getDate();
	}
}
