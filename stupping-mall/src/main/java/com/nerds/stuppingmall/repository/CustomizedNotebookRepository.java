package com.nerds.stuppingmall.repository;

import java.util.List;

import com.nerds.stuppingmall.dto.NotebookResponseBasicDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.nerds.stuppingmall.dto.CategoryInfoRequestDto;
import com.nerds.stuppingmall.dto.NotebookListResponseDto;

public interface CustomizedNotebookRepository {
	Page<NotebookResponseBasicDto> customFindNotebookBasicDtosBySupplierId(Pageable pageable, Sort sort, String supplierId);
	Page<NotebookResponseBasicDto> customFindNotebookBasicDtosByName(Pageable pageable, Sort sort, String name);
	Page<NotebookListResponseDto> customFindNotebooksBySupplierId(Pageable pageable, Sort sort, String supplierId);
	Page<NotebookListResponseDto> customFindNotebooksByName(Pageable pageable, Sort sort, String name);
	Page<NotebookListResponseDto> customFindNotebooksByCategory(Pageable pageable, Sort sort, CategoryInfoRequestDto categoryInfoRequestDto);
	Page<NotebookListResponseDto> customFindNotebooksByNotebookIds(Pageable pageable, List<String> notebookIds);
}
