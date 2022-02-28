package com.nerds.stuppingmall.dto;

import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderSalesInfoResponseDto {
	private Long maxValue;
	private Map<String, Long> sales;
}
