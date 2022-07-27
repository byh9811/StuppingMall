package com.nerds.stuppingmall.service.notebook;

import java.util.List;
import java.util.stream.Collectors;

import com.nerds.stuppingmall.dto.*;
import com.nerds.stuppingmall.util.SearchParameterInitializer;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.repository.member.MemberRepository;
import com.nerds.stuppingmall.repository.NotebookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotebookSearchService {
	final MongoTemplate mongoTemplate;
	final MemberRepository memberRepository;
	final NotebookRepository notebookRepository;
	final SearchParameterInitializer searchParameterInitializer;

	public List<NotebookDto.IdImgResponse> getTop3Notebooks() {
		return notebookRepository.findBy(searchParameterInitializer.getPageable(0, 3, "판매순"))
				.stream().map(NotebookDto.IdImgResponse::new).collect(Collectors.toList());
	}

	public List<NotebookDto.ListResponse> getNew8Notebooks() {
		return notebookRepository.findBy(searchParameterInitializer.getPageable(0, 8))
				.stream().map(NotebookDto.ListResponse::new).collect(Collectors.toList());
	}

	public Page<NotebookDto.ListResponse> getNotebookList(int curPage, String sortingOrder, String name) {
		return notebookRepository.customFindNotebooksByName(searchParameterInitializer.getPageable(curPage),
				searchParameterInitializer.getSortMethod(sortingOrder), name);
	}

	public Page<NotebookDto.IdNameResponse> getMyNotebook(int curPage, String sortingOrder, String supplierId, String name) {
		return notebookRepository.customFindMyNotebookList(searchParameterInitializer.getPageable(curPage),
				searchParameterInitializer.getSortMethod(sortingOrder), supplierId, name);
	}

	public Page<NotebookDto.ListResponse> findNotebooksByCategory(int curPage, String sortingOrder, CategoryInfoRequestDto categoryInfoRequestDto) {
		return notebookRepository.customFindNotebooksByCategory(searchParameterInitializer.getPageable(curPage),
				searchParameterInitializer.getSortMethod(sortingOrder), categoryInfoRequestDto);
	}
	
	public Page<NotebookDto.ListResponse> getMyPicks(int curPage, String customerId) {
		return notebookRepository.customFindNotebooksByNotebookIds(searchParameterInitializer.getPageable(curPage),
				memberRepository.findById(customerId).get().getMyPicks());
	}
}
