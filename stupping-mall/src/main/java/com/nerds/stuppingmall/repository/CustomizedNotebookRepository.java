package com.nerds.stuppingmall.repository;

import java.util.List;

import com.nerds.stuppingmall.dto.NotebookResponseBasicDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.nerds.stuppingmall.dto.NotebookInfoRequestDto;
import com.nerds.stuppingmall.dto.NotebookListResponseDto;

public interface CustomizedNotebookRepository {
	Page<NotebookResponseBasicDto> customFindNotebookBasicDtosBySupplierId(Pageable pageable, Sort sort, String supplierId);
	Page<NotebookResponseBasicDto> customFindNotebookBasicDtosByName(Pageable pageable, Sort sort, String name);
	Page<NotebookListResponseDto> customFindNotebooksBySupplierId(Pageable pageable, Sort sort, String supplierId);
	Page<NotebookListResponseDto> customFindNotebooksByName(Pageable pageable, Sort sort, String name);
	Page<NotebookListResponseDto> customFindNotebooksByCategory(Pageable pageable, Sort sort, NotebookInfoRequestDto notebookInfoRequestDto);
	Page<NotebookListResponseDto> customFindNotebooksByNotebookIds(Pageable pageable, List<String> notebookIds);
}
