package com.nerds.stuppingmall.service.category;

import com.nerds.stuppingmall.domain.Category;
import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryStatusService {
	final CategoryRepository categoryRepository;
	
	public Map<String, List<String>> getCategories() {
		List<Category> categories = categoryRepository.findAll();
		Map<String, List<String>> ret = new HashMap<>();

		for(Category category: categories) {
			String cateId = category.get_id();
			cateId = cateId.substring(0, 1).toLowerCase() + cateId.substring(1);
			ret.put(cateId, category.getList());
		}

		return ret;
	}

	public List<String> getCategoryDetail(String category) {
		return categoryRepository.findById(category).get().getList();
	}
}
