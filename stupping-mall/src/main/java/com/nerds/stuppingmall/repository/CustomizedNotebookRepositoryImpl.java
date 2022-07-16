package com.nerds.stuppingmall.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.nerds.stuppingmall.domain.Notebook;
import com.nerds.stuppingmall.dto.CategoryInfoRequestDto;
import com.nerds.stuppingmall.dto.NotebookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;

public class CustomizedNotebookRepositoryImpl implements CustomizedNotebookRepository {
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public Page<NotebookDto.IdNameResponse> customFindMyNotebookList(Pageable pageable, Sort sort, String supplierId, String name) {
		Query query = setDefaultQuery(pageable, sort);
		query.addCriteria(Criteria.where("name").regex(name).orOperator(Criteria.where("supplierId").is(supplierId)));

		List<NotebookDto.IdNameResponse> notebooks = mongoTemplate.find(query, NotebookDto.IdNameResponse.class, "notebooks");

		return PageableExecutionUtils.getPage(
				notebooks, pageable,
				() -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), NotebookDto.IdNameResponse.class));
	}

	// 쓰이는지 모르겠는데 일단 보류
	@Override
	public Page<NotebookDto.ListResponse> customFindNotebooksBySupplierId(Pageable pageable, Sort sort, String supplierId) {
		Query query = setDefaultQuery(pageable, sort);
		query.addCriteria(Criteria.where("supplierId").is(supplierId));

		return getListResponseResult(query, pageable);
	}

	@Override
	public Page<NotebookDto.ListResponse> customFindNotebooksByName(Pageable pageable, Sort sort, String name) {
		Query query = setDefaultQuery(pageable, sort);
		query.addCriteria(Criteria.where("name").regex(name));

		return getListResponseResult(query, pageable);
	}

	@Override
	public Page<NotebookDto.ListResponse> customFindNotebooksByCategory(Pageable pageable, Sort sort, CategoryInfoRequestDto categoryInfoRequestDto) {
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

		return getListResponseResult(query, pageable);
	}

	@Override
	public Page<NotebookDto.ListResponse> customFindNotebooksByNotebookIds(Pageable pageable, List<String> notebookIds) {
		List<Criteria> criterias = new ArrayList<>();
		Query query = new Query();
		for(String notebookId: notebookIds)
			criterias.add(Criteria.where("notebookId").is(notebookId));
		Criteria criteria = new Criteria();
		query.addCriteria(criteria.orOperator(criterias));
		query.with(pageable);

		return getListResponseResult(query, pageable);
	}

	private Query setDefaultQuery(Pageable pageable, Sort sort) {
		Query query = new Query();
		query.with(pageable);
		query.with(sort);

		return query;
	}

	private Page<NotebookDto.ListResponse> getListResponseResult(Query query, Pageable pageable) {
		List<Notebook> notebooks = mongoTemplate.find(query, Notebook.class, "notebooks");

		return PageableExecutionUtils.getPage(
				notebooks.stream().map(NotebookDto.ListResponse::new).collect(Collectors.toList()), pageable,
				() -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), NotebookDto.ListResponse.class));
	}
}
