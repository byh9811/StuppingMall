package com.nerds.stuppingmall.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotebookRecent8ResponseDto {
    private String id;
    private String name;
    private int price;
    private String img;
    private String cpuName;
    private double weight;
    private double screenSize;
    private int ramSize;
}
