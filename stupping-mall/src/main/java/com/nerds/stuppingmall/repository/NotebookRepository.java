package com.nerds.stuppingmall.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.nerds.stuppingmall.domain.Notebook;

public interface NotebookRepository extends MongoRepository<Notebook, String> {
	List<Notebook> findByName(String name);
	List<Notebook> findBySupplierId(String supplierId);
}