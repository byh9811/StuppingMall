package com.nerds.stuppingmall.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Order {
	@Id
	private String _id;
	private String customerId;
	private String supplierId;
	private String notebookId;
	private String status;
	private String payDate;
	private String deliveredDate;
	private int payment;
}
