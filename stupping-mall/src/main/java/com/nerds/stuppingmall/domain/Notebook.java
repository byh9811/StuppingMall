package com.nerds.stuppingmall.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="notebooks")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Notebook {
	@Id
	private String _id;
	private String name;
	private String supplierId;
	private String supplierName;
	private String registerDate;
	private String img;
	private int price;
	private int view;
	private double rate;
	private int salesVolume;
	private String cpuName;
	private String gpuName;
	private double weight;
	private double screenSize;
	private int cpuScore;
	private int gpuScore;
	private int ramSize;
	private int ssdSize;
	private int hddSize;
	private double batterySize;
	private String usage;
	
	@Override
	public boolean equals(Object o) {
		return _id.equals(((Notebook)o).get_id());
	}
}
