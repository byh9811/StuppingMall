package com.nerds.stuppingmall.repository;

import java.util.ArrayList;
import java.util.List;

import com.nerds.stuppingmall.dto.CategoryInfoRequestDto;
import com.nerds.stuppingmall.dto.NotebookResponseBasicDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;

import com.nerds.stuppingmall.dto.NotebookListResponseDto;

public class CustomizedNotebookRepositoryImpl implements CustomizedNotebookRepository {
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public Page<NotebookResponseBasicDto> customFindNotebookBasicDtosBySupplierId(Pageable pageable, Sort sort, String supplierId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("supplierId").is(supplierId));
		query.with(sort);
		query.with(pageable);

		List<NotebookResponseBasicDto> notebooks = mongoTemplate.find(query, NotebookResponseBasicDto.class, "notebooks");

		return PageableExecutionUtils.getPage(
				notebooks, pageable,
				() -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), NotebookResponseBasicDto.class));
	}

	@Override
	public Page<NotebookResponseBasicDto> customFindNotebookBasicDtosByName(Pageable pageable, Sort sort, String name) {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").regex(name));
		query.with(sort);
		query.with(pageable);

		List<NotebookResponseBasicDto> notebooks = mongoTemplate.find(query, NotebookResponseBasicDto.class, "notebooks");

		return PageableExecutionUtils.getPage(
				notebooks, pageable,
				() -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), NotebookResponseBasicDto.class));
	}

	@Override
	public Page<NotebookListResponseDto> customFindNotebooksBySupplierId(Pageable pageable, Sort sort, String supplierId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("supplierId").is(supplierId));
		query.with(sort);
		query.with(pageable);
		
		List<NotebookListResponseDto> notebooks = mongoTemplate.find(query, NotebookListResponseDto.class, "notebooks");

		return PageableExecutionUtils.getPage(
				notebooks, pageable,
				() -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), NotebookListResponseDto.class));
	}

	@Override
	public Page<NotebookListResponseDto> customFindNotebooksByName(Pageable pageable, Sort sort, String name) {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").regex(name));
		query.with(sort);
		query.with(pageable);
		
		List<NotebookListResponseDto> notebooks = mongoTemplate.find(query, NotebookListResponseDto.class, "notebooks");
		
		return PageableExecutionUtils.getPage(
				notebooks, pageable,
				() -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), NotebookListResponseDto.class));
	}

	@Override
	public Page<NotebookListResponseDto> customFindNotebooksByCategory(Pageable pageable, Sort sort,
																	   CategoryInfoRequestDto categoryInfoRequestDto) {
		
		Query query = new Query();
		List<Criteria> outerCriterias = new ArrayList<>();
		
		if(!categoryInfoRequestDto.getSupplierNames().isEmpty()) {
			Criteria criteria = new Criteria();
			Criteria[] innerCriterias = new Criteria[categoryInfoRequestDto.getSupplierNames().size()];
			for(int i=0; i<categoryInfoRequestDto.getSupplierNames().size(); i++) {
				innerCriterias[i] = Criteria.where("supplierName").is(categoryInfoRequestDto.getSupplierNames().get(i));
			}
			outerCriterias.add(criteria.orOperator(innerCriterias));
		}
		if(!categoryInfoRequestDto.getCpuNames().isEmpty()) {
			Criteria criteria = new Criteria();
			Criteria[] innerCriterias = new Criteria[categoryInfoRequestDto.getCpuNames().size()];
			for(int i=0; i<categoryInfoRequestDto.getCpuNames().size(); i++) {
				innerCriterias[i] = Criteria.where("cpuName").is(categoryInfoRequestDto.getCpuNames().get(i));
			}
			outerCriterias.add(criteria.orOperator(innerCriterias));
		}
		if(!categoryInfoRequestDto.getGpuNames().isEmpty()) {
			Criteria criteria = new Criteria();
			Criteria[] innerCriterias = new Criteria[categoryInfoRequestDto.getGpuNames().size()];
			for(int i=0; i<categoryInfoRequestDto.getGpuNames().size(); i++) {
				innerCriterias[i] = Criteria.where("gpuName").is(categoryInfoRequestDto.getGpuNames().get(i));
			}
			outerCriterias.add(criteria.orOperator(innerCriterias));
		}
		if(!categoryInfoRequestDto.getRegisterYears().isEmpty()) {
			Criteria criteria = new Criteria();
			Criteria[] innerCriterias = new Criteria[categoryInfoRequestDto.getRegisterYears().size()];
			for(int i=0; i<categoryInfoRequestDto.getRegisterYears().size(); i++) {
				innerCriterias[i] = Criteria.where("registerDate").regex("("+categoryInfoRequestDto.getRegisterYears().get(i)+")+");
			}
			outerCriterias.add(criteria.orOperator(innerCriterias));
		}
		
		if(!outerCriterias.isEmpty()) {
			Criteria criteria = new Criteria();
			query.addCriteria(criteria.andOperator(outerCriterias));
			query.with(sort);
			query.with(pageable);
		}
		
		List<NotebookListResponseDto> notebooks = mongoTemplate.find(query, NotebookListResponseDto.class, "notebooks");
		
		return PageableExecutionUtils.getPage(
				notebooks, pageable,
				() -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), NotebookListResponseDto.class));
	}

	@Override
	public Page<NotebookListResponseDto> customFindNotebooksByNotebookIds(Pageable pageable, List<String> notebookIds) {
		List<Criteria> criterias = new ArrayList<>();
		Query query = new Query();
		for(String notebookId: notebookIds)
			criterias.add(Criteria.where("notebookId").is(notebookId));
		Criteria criteria = new Criteria();
		query.addCriteria(criteria.orOperator(criterias));
		query.with(pageable);
		
		List<NotebookListResponseDto> notebooks = mongoTemplate.find(query, NotebookListResponseDto.class, "notebooks");
		
		return PageableExecutionUtils.getPage(
				notebooks, pageable,
				() -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), NotebookListResponseDto.class));
	}
}
