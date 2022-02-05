package com.nerds.stuppingmall.dto;

import com.nerds.stuppingmall.enumerate.Usage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NotebookInfoResponseDto {
	private String name;
	private String supplierName;
	private String manufactureDate;
	private String img;
	private int price;
	private int view;
	private double rate;
	private int salesVolume;
	private String cpuName;
	private String gpuName;
	private double weight;
	private int screenSize;
	private int ramSize;
	private int ssdSize;
	private int hddSize;
	private int batterySize;
	private Usage usage;
}
