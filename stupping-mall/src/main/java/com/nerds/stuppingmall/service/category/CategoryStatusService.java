package com.nerds.stuppingmall.service.category;

import com.nerds.stuppingmall.domain.Category;
import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryStatusService {
	final CategoryRepository categoryRepository;
	
	public List<String> getCategoryNames() {
		List<Category> categories = categoryRepository.findAll();

		return categories.stream().map(category -> category.get_id()).collect(Collectors.toList());
	}

	public List<String> getCategoryDetail(String category) {
		return categoryRepository.findById(category).get().getList();
	}
}
