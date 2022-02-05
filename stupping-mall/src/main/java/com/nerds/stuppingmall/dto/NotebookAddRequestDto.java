package com.nerds.stuppingmall.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class NotebookAddRequestDto {
	private String name;
	private String manufactureDate;
	private MultipartFile imgFile;
	private int price;
	private String cpuName;
	private String gpuName;
	private double weight;
	private int screenSize;
	private int ramSize;
	private int ssdSize;
	private int hddSize;
	private int batterySize;
}
