package com.nerds.stuppingmall.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CategoryInfoRequestDto {
	private List<String> supplierNames;
	private List<String> cpuNames;
	private List<String> gpuNames;
	private List<String> registerYears;
}
