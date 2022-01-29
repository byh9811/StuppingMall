package com.nerds.stuppingmall.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.nerds.stuppingmall.service.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Document(collection="reviews")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Slf4j
public class Review {
	@Id
	private String productId;
	private String userId;
	private int rate;
	private String comment;
}
