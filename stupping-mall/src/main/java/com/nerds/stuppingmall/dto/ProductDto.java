package com.nerds.stuppingmall.dto;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {
	@Id
	private String _id;
	private String name;
	private String category;
	private int price;
	private String img;
	private String seller;
	private int view;
	private double rate;
	private int salesVolume;
	private String date;
}
