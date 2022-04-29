package com.nerds.stuppingmall.service.category;

import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.dto.NotebookInfoRequestDto;
import com.nerds.stuppingmall.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryStatusService {
	final CategoryRepository categoryRepository;
	
	public NotebookInfoRequestDto getExistingCategories() {
		return NotebookInfoRequestDto.builder()
							.supplierNames(categoryRepository.findById("SupplierName").get().getList())
							.cpuNames(categoryRepository.findById("CpuName").get().getList())
							.gpuNames(categoryRepository.findById("GpuName").get().getList())
							.registerYears(categoryRepository.findById("RegistYear").get().getList())
							.build();
	}
}
