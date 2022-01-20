package com.nerds.stuppingmall.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Document(collection="products")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Slf4j
public class Product {
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
