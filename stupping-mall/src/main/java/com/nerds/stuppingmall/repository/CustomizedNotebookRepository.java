package com.nerds.stuppingmall.repository;

import java.util.List;

import com.nerds.stuppingmall.dto.NotebookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.nerds.stuppingmall.dto.CategoryInfoRequestDto;

public interface CustomizedNotebookRepository {
	Page<NotebookDto.IdNameResponse> customFindNotebookBasicDtosBySupplierId(Pageable pageable, Sort sort, String supplierId);
	Page<NotebookDto.IdNameResponse> customFindNotebookBasicDtosByName(Pageable pageable, Sort sort, String name);
	Page<NotebookDto.ListResponse> customFindNotebooksBySupplierId(Pageable pageable, Sort sort, String supplierId);
	Page<NotebookDto.ListResponse> customFindNotebooksByName(Pageable pageable, Sort sort, String name);
	Page<NotebookDto.ListResponse> customFindNotebooksByCategory(Pageable pageable, Sort sort, CategoryInfoRequestDto categoryInfoRequestDto);
	Page<NotebookDto.ListResponse> customFindNotebooksByNotebookIds(Pageable pageable, List<String> notebookIds);
}
