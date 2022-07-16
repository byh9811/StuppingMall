package com.nerds.stuppingmall.dto;

import com.nerds.stuppingmall.domain.Notebook;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class NotebookDto {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
        private String name;
        private String registerDate;
        private List<MultipartFile> imgFiles;
        private Integer price;
        private String cpuName;
        private String gpuName;
        private Double weight;
        private Double screenSize;
        private Integer ramSize;
        private Integer ssdSize;
        private Integer hddSize;
        private Double batterySize;

        public Notebook toNotebook(String supplierId, List<String> urls, int cpuScore, int gpuScore) {
            Notebook notebook = Notebook.builder()
                    .name(name)
                    .supplierId(supplierId)
                    .registerDate(registerDate)
                    .imgs(urls)
                    .price(price)
                    .view(0)
                    .rate(0.0)
                    .salesVolume(0)
                    .cpuName(cpuName)
                    .gpuName(gpuName)
                    .weight(weight)
                    .screenSize(screenSize)
                    .cpuScore(cpuScore)
                    .gpuScore(gpuScore)
                    .ramSize(ramSize)
                    .ssdSize(ssdSize)
                    .hddSize(hddSize)
                    .batterySize(batterySize)
                    .build();
            return notebook;
        }
    }

    @Getter
    public static class ListResponse {
        private String id;
        private String name;
        private Integer price;
        private String img;
        private String cpuName;
        private Double weight;
        private Double screenSize;
        private Integer ramSize;

        public ListResponse(Notebook notebook) {
            this.id = notebook.get_id();
            this.name = notebook.getName();
            this.price = notebook.getPrice();
            this.img = notebook.getImgs().get(0);
            this.cpuName = notebook.getCpuName();
            this.weight = notebook.getWeight();
            this.screenSize = notebook.getScreenSize();
            this.ramSize = notebook.getRamSize();
        }
    }

    @Getter
    public static class IdNameResponse {
        private String id;
        private String name;

        public IdNameResponse(Notebook notebook) {
            this.id = notebook.get_id();
            this.name = notebook.getName();
        }
    }

    @Getter
    public static class CompareInfoResponse {
        private Integer cpuScore;
        private Integer gpuScore;
        private Integer ramSize;
        private Double weight;
        private Double batterySize;
        private Double screenSize;

        public CompareInfoResponse(Notebook notebook) {
            this.cpuScore = notebook.getCpuScore();
            this.gpuScore = notebook.getGpuScore();
            this.ramSize = notebook.getRamSize();
            this.weight = notebook.getWeight();
            this.batterySize = notebook.getBatterySize();
            this.screenSize = notebook.getScreenSize();
        }
    }

    @Getter
    public static class IdImgResponse {
        private String id;
        private String img;

        public IdImgResponse(Notebook notebook) {
            this.id = notebook.get_id();
            this.img = notebook.getImgs().get(0);
        }
    }
}