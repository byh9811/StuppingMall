package com.nerds.stuppingmall.repository.notebook;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.nerds.stuppingmall.domain.Notebook;

public interface NotebookRepository extends MongoRepository<Notebook, String>, CustomizedNotebookRepository {
	List<Notebook> findBy(Pageable pageable);
	List<Notebook> findByName(String name);
	List<Notebook> findBySupplierId(String supplierId);
	List<String> findImgsBy_id(String _id);
}