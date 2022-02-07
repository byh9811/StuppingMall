package com.nerds.stuppingmall.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.domain.Notebook;
import com.nerds.stuppingmall.dto.NotebookAddRequestDto;
import com.nerds.stuppingmall.dto.NotebookInfoResponseDto;
import com.nerds.stuppingmall.enumerate.Usage;
import com.nerds.stuppingmall.repository.MemberRepository;
import com.nerds.stuppingmall.repository.NotebookRepository;

@Service
public class NotebookService {
	@Autowired
	NotebookRepository notebookRepository;
	@Autowired
	MemberRepository memberRepository;
	
	public NotebookInfoResponseDto getNotebook(String id) {
		Optional<Notebook> notebookWrapper = notebookRepository.findById(id);

		if(!notebookWrapper.isPresent())
			throw new NoSuchElementException("해당 상품이 존재하지 않습니다!!");
		
		Notebook notebook = notebookWrapper.get();
		
		return NotebookInfoResponseDto.builder()
				.name(notebook.getName())
				.supplierName(memberRepository.findById(notebook.getSupplierId()).get().getName())
				.manufactureDate(notebook.getManufactureDate())
				.img(notebook.getImg())
				.price(notebook.getPrice())
				.view(notebook.getView())
				.rate(notebook.getRate())
				.salesVolume(notebook.getSalesVolume())
				.cpuName(notebook.getCpuName())
				.gpuName(notebook.getGpuName())
				.weight(notebook.getWeight())
				.screenSize(notebook.getScreenSize())
				.ramSize(notebook.getRamSize())
				.ssdSize(notebook.getSsdSize())
				.hddSize(notebook.getHddSize())
				.batterySize(notebook.getBatterySize())
				.usage(Usage.valueOf(notebook.getUsage()))
				.build();
	}

	public String addNotebook(String supplierId, NotebookAddRequestDto notebookAddRequestDto) {
		String basePath = "C:\\img";
		String filePath = basePath + "/" + supplierId;
		String url = null;
		File folder = new File(filePath);
		
		if(!folder.exists())
			folder.mkdirs();
		
		if(!notebookAddRequestDto.getImgFile().isEmpty()) {
            // jpeg, png, gif 파일들만 받아서 처리
            String contentType = notebookAddRequestDto.getImgFile().getContentType();
            String originalFileExtension;
            
            // 확장자 명이 없으면 이 파일은 잘 못 된 것이다
            if (!contentType.isEmpty()) {
                if(contentType.contains("image/jpeg")){
                    originalFileExtension = ".jpg";
                }
                else if(contentType.contains("image/png")){
                    originalFileExtension = ".png";
                }
                else if(contentType.contains("image/gif")){
                    originalFileExtension = ".gif";
                }
                // 다른 파일 명이면 에러 발생
                else {
                	throw new IllegalStateException("허용된 형식이 아닙니다!!");
                }
                // 각 이름은 겹치면 안되므로 나노 초까지 동원하여 지정
                String fileName = notebookAddRequestDto.getName() + originalFileExtension;

                // 저장된 파일로 변경하여 이를 보여주기 위함
                url = filePath + "/" + fileName;
                File file = new File(url);
                try {
                	notebookAddRequestDto.getImgFile().transferTo(file);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
		}
		
		Notebook notebook = Notebook.builder()
								.name(notebookAddRequestDto.getName())
								.supplierId(supplierId)
								.manufactureDate(notebookAddRequestDto.getManufactureDate())
								.img(url)
								.price(notebookAddRequestDto.getPrice())
								.view(0)
								.rate(0.0)
								.salesVolume(0)
								.cpuName(notebookAddRequestDto.getCpuName())
								.gpuName(notebookAddRequestDto.getGpuName())
								.weight(notebookAddRequestDto.getWeight())
								.screenSize(notebookAddRequestDto.getScreenSize())
								.ramSize(notebookAddRequestDto.getRamSize())
								.ssdSize(notebookAddRequestDto.getSsdSize())
								.hddSize(notebookAddRequestDto.getHddSize())
								.batterySize(notebookAddRequestDto.getBatterySize())
								.usage(Usage.DOCUMENT.getValue())
								.build();
								
		return notebookRepository.save(notebook).get_id();
	}

	public List<NotebookInfoResponseDto> getNotebooks(String name) {
		List<Notebook> notebooks = notebookRepository.findByName(name);
		List<NotebookInfoResponseDto> notebookDtos = new ArrayList<>();
		for(Notebook notebook: notebooks) {
			notebookDtos.add(NotebookInfoResponseDto.builder()
					.name(notebook.getName())
					.supplierName(memberRepository.findById(notebook.getSupplierId()).get().getName())
					.manufactureDate(notebook.getManufactureDate())
					.img(notebook.getImg())
					.price(notebook.getPrice())
					.view(notebook.getView())
					.rate(notebook.getRate())
					.salesVolume(notebook.getSalesVolume())
					.cpuName(notebook.getCpuName())
					.gpuName(notebook.getGpuName())
					.weight(notebook.getWeight())
					.screenSize(notebook.getScreenSize())
					.ramSize(notebook.getRamSize())
					.ssdSize(notebook.getSsdSize())
					.hddSize(notebook.getHddSize())
					.batterySize(notebook.getBatterySize())
					.usage(Usage.valueOf(notebook.getUsage()))
					.build());
		}
		return notebookDtos;
	}

	public List<NotebookInfoResponseDto> getMyNotebooks(String supplierId) {
		List<Notebook> myNotebooks = notebookRepository.findBySupplierId(supplierId);
		List<NotebookInfoResponseDto> notebookDtos = new ArrayList<>();
		String supplierName = memberRepository.findById(supplierId).get().getName();
		
		for(Notebook notebook: myNotebooks) {
			notebookDtos.add(NotebookInfoResponseDto.builder()
					.name(notebook.getName())
					.supplierName(supplierName)
					.manufactureDate(notebook.getManufactureDate())
					.img(notebook.getImg())
					.price(notebook.getPrice())
					.view(notebook.getView())
					.rate(notebook.getRate())
					.salesVolume(notebook.getSalesVolume())
					.cpuName(notebook.getCpuName())
					.gpuName(notebook.getGpuName())
					.weight(notebook.getWeight())
					.screenSize(notebook.getScreenSize())
					.ramSize(notebook.getRamSize())
					.ssdSize(notebook.getSsdSize())
					.hddSize(notebook.getHddSize())
					.batterySize(notebook.getBatterySize())
					.usage(Usage.valueOf(notebook.getUsage()))
					.build());
		}
		return notebookDtos;
	}

	public void deleteNotebook(String supplierId, String notebookId) {
		Optional<Notebook> notebookWrapper = notebookRepository.findById(notebookId);
		if(!notebookWrapper.isPresent())
			throw new NoSuchElementException("존재하지 않는 상품입니다!!");
		if(!notebookWrapper.get().getSupplierId().equals(supplierId))
			throw new NoSuchElementException("잘못된 접근입니다!!");
		
		Notebook notebook = notebookWrapper.get();
		notebookRepository.deleteById(notebook.get_id());
		File file = new File(notebook.getImg());
		if(file.exists())
			file.delete();
	}
}
