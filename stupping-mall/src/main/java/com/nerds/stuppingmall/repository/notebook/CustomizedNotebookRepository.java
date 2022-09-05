package com.nerds.stuppingmall.repository.notebook;

import java.util.List;

import com.nerds.stuppingmall.dto.NotebookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.nerds.stuppingmall.dto.CategoryInfoRequestDto;

public interface CustomizedNotebookRepository {
	Page<NotebookDto.IdNameResponse> customFindMyNotebookList(Pageable pageable, Sort sort, String supplierId, String name);
	Page<NotebookDto.ListResponse> customFindNotebooksBySupplierId(Pageable pageable, Sort sort, String supplierId);
//	Page<NotebookDto.ListResponse> customFindNotebooksByName(Pageable pageable, Sort sort, String name);
	Page<NotebookDto.ListResponse> customFindNotebooks(Pageable pageable, Sort sort, String name, CategoryInfoRequestDto categoryInfoRequestDto);
	Page<NotebookDto.ListResponse> customFindNotebooksByNotebookIds(Pageable pageable, List<String> notebookIds);
}
