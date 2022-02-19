package com.nerds.stuppingmall.service.category;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.domain.Category;
import com.nerds.stuppingmall.domain.Notebook;
import com.nerds.stuppingmall.dto.NotebookAddRequestDto;
import com.nerds.stuppingmall.dto.NotebookInfoRequestDto;
import com.nerds.stuppingmall.dto.NotebookInfoResponseDto;
import com.nerds.stuppingmall.enumerate.Usage;
import com.nerds.stuppingmall.repository.CategoryRepository;
import com.nerds.stuppingmall.repository.MemberRepository;
import com.nerds.stuppingmall.repository.NotebookRepository;

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
