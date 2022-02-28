package com.nerds.stuppingmall.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.nerds.stuppingmall.dto.NotebookInfoRequestDto;
import com.nerds.stuppingmall.dto.NotebookInfoResponseDto;

public interface CustomizedNotebookRepository {
	Page<NotebookInfoResponseDto> customFindNotebooksBySupplierId(Pageable pageable, Sort sort, String supplierId);
	Page<NotebookInfoResponseDto> customFindNotebooksByName(Pageable pageable, Sort sort, String name);
	Page<NotebookInfoResponseDto> customFindNotebooksByCategory(Pageable pageable, Sort sort, NotebookInfoRequestDto notebookInfoRequestDto);
	Page<NotebookInfoResponseDto> customFindNotebooksByNotebookIds(Pageable pageable, List<String> notebookIds);
}
