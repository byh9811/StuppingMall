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
public class NotebookCompareInfoResponseDto {
	private int cpuScore;
	private int gpuScore;
	private int ramSize;
	private double weight;
	private double batterySize;
	private double screenSize;
}
