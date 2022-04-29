package com.nerds.stuppingmall.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NotebookListResponseDto {
	private String id;
	private String name;
	private String img;
	private int price;
	private String cpuName;
}
