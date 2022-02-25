package com.nerds.stuppingmall.service.notebook;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.domain.Notebook;
import com.nerds.stuppingmall.dto.NotebookInfoResponseDto;
import com.nerds.stuppingmall.enumerate.Usage;
import com.nerds.stuppingmall.repository.MemberRepository;
import com.nerds.stuppingmall.repository.NotebookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotebookDetailsService {
	final NotebookRepository notebookRepository;
	final MemberRepository memberRepository;
	
	public NotebookInfoResponseDto findNotebook(String id) {
		Optional<Notebook> notebookWrapper = notebookRepository.findById(id);

		if(!notebookWrapper.isPresent())
			throw new NoSuchElementException("해당 상품이 존재하지 않습니다!!");
		
		Notebook notebook = notebookWrapper.get();
		
		return NotebookInfoResponseDto.builder()
				.name(notebook.getName())
				.supplierName(memberRepository.findById(notebook.getSupplierId()).get().getName())
				.registerDate(notebook.getRegisterDate())
				.imgs(notebook.getImgs())
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
}
