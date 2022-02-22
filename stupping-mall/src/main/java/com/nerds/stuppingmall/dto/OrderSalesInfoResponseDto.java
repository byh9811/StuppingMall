package com.nerds.stuppingmall.dto;

import java.util.HashMap;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderSalesInfoResponseDto {
	private Long maxValue;
	private HashMap<String, Long> sales;
}
