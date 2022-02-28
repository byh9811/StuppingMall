package com.nerds.stuppingmall.dto;

import java.util.List;

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
	private String registerDate;
	private List<String> imgs;
	private int price;
	private int view;
	private double rate;
	private int salesVolume;
	private String cpuName;
	private String gpuName;
	private double weight;
	private double screenSize;
	private int ramSize;
	private int ssdSize;
	private int hddSize;
	private double batterySize;
	private Usage usage;
}
