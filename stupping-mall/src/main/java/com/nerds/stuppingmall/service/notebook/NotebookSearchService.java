package com.nerds.stuppingmall.service.notebook;

import java.util.ArrayList;
import java.util.List;

import com.nerds.stuppingmall.dto.*;
import com.nerds.stuppingmall.util.SearchParameterInitializer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.domain.Notebook;
import com.nerds.stuppingmall.repository.MemberRepository;
import com.nerds.stuppingmall.repository.NotebookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotebookSearchService {
	final MongoTemplate mongoTemplate;
	final MemberRepository memberRepository;
	final NotebookRepository notebookRepository;
	final int SIZE_PER_PAGE = 10;
	final SearchParameterInitializer searchParameterInitializer;
	
	public List<NotebookDto.ListResponse> getNew8Notebooks() {
		List<Notebook> notebooks = notebookRepository.findBy(PageRequest.of(0, 8, Sort.by(Sort.Direction.DESC, "registerDate")));
		List<NotebookDto.ListResponse> notebookList = new ArrayList<>();

		for(Notebook notebook: notebooks)
			notebookList.add(new NotebookDto.ListResponse(notebook));

		return notebookList;
	}

	public List<NotebookDto.ListResponse> getNotebooksTemp() {
		List<Notebook> notebooks = notebookRepository.findBy(PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "registerDate")));
		List<NotebookDto.ListResponse> notebookList = new ArrayList<>();

		for(Notebook notebook: notebooks)
			notebookList.add(new NotebookDto.ListResponse(notebook));

		return notebookList;
	}

	public List<NotebookDto.IdImgResponse> getTop3Notebooks() {
		List<Notebook> notebooks = notebookRepository.findBy(PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "salesVolume")));
		List<NotebookDto.IdImgResponse> notebookDtos = new ArrayList<>();


		for(Notebook notebook: notebooks)
			notebookDtos.add(new NotebookDto.IdImgResponse(notebook));

		return notebookDtos;
	}

	public Page<NotebookDto.IdNameResponse> getMyNotebook(int curPage, String sortingOrder, String supplierId) {
		Pageable pageable = PageRequest.of(curPage, SIZE_PER_PAGE);
		Sort sort;

		switch(sortingOrder) {
			case "최신순": sort = Sort.by(Sort.Direction.DESC, "registerDate"); break;
			case "인기순": sort = Sort.by(Sort.Direction.DESC, "rate"); break;
			case "판매순": sort = Sort.by(Sort.Direction.DESC, "salesVolume"); break;
			case "조회순": sort = Sort.by(Sort.Direction.DESC, "view"); break;
			case "낮은가격순": sort = Sort.by(Sort.Direction.ASC, "price"); break;
			case "높은가격순": sort = Sort.by(Sort.Direction.DESC, "price"); break;
			default: throw new RuntimeException();
		}

		return notebookRepository.customFindNotebookBasicDtosBySupplierId(pageable, sort, supplierId);
	}

	public Page<NotebookDto.ListResponse> findNotebooksBySupplierId(int curPage, String sortingOrder, String supplierId) {
		Pageable pageable = PageRequest.of(curPage, SIZE_PER_PAGE);
		Sort sort;

		switch(sortingOrder) {
		case "최신순": sort = Sort.by(Sort.Direction.DESC, "registerDate"); break;
		case "인기순": sort = Sort.by(Sort.Direction.DESC, "rate"); break;
		case "판매순": sort = Sort.by(Sort.Direction.DESC, "salesVolume"); break;
		case "조회순": sort = Sort.by(Sort.Direction.DESC, "view"); break;
		case "낮은가격순": sort = Sort.by(Sort.Direction.ASC, "price"); break;
		case "높은가격순": sort = Sort.by(Sort.Direction.DESC, "price"); break;
		default: throw new RuntimeException();
		}

		return notebookRepository.customFindNotebooksBySupplierId(pageable, sort, supplierId);
	}

	public Page<NotebookDto.IdNameResponse> findNotebookBasicDtosByName(int curPage, String sortingOrder, String name) {
		Pageable pageable = PageRequest.of(curPage, SIZE_PER_PAGE);
		Sort sort;

		switch(sortingOrder) {
			case "최신순": sort = Sort.by(Sort.Direction.DESC, "registerDate"); break;
			case "인기순": sort = Sort.by(Sort.Direction.DESC, "rate"); break;
			case "판매순": sort = Sort.by(Sort.Direction.DESC, "salesVolume"); break;
			case "조회순": sort = Sort.by(Sort.Direction.DESC, "view"); break;
			case "낮은가격순": sort = Sort.by(Sort.Direction.ASC, "price"); break;
			case "높은가격순": sort = Sort.by(Sort.Direction.DESC, "price"); break;
			default: throw new RuntimeException();
		}

		return notebookRepository.customFindNotebookBasicDtosByName(pageable, sort, name);
	}

	public Page<NotebookDto.ListResponse> findNotebooksByName(int curPage, String sortingOrder, String name) {
		Pageable pageable = PageRequest.of(curPage, SIZE_PER_PAGE);
		Sort sort;
		
		switch(sortingOrder) {
		case "최신순": sort = Sort.by(Sort.Direction.DESC, "registerDate"); break;
		case "인기순": sort = Sort.by(Sort.Direction.DESC, "rate"); break;
		case "판매순": sort = Sort.by(Sort.Direction.DESC, "salesVolume"); break;
		case "조회순": sort = Sort.by(Sort.Direction.DESC, "view"); break;
		case "낮은가격순": sort = Sort.by(Sort.Direction.ASC, "price"); break;
		case "높은가격순": sort = Sort.by(Sort.Direction.DESC, "price"); break;
		default: throw new RuntimeException();
		}
		
		return notebookRepository.customFindNotebooksByName(pageable, sort, name);
	}

	public Page<NotebookDto.ListResponse> findNotebooksByCategory(int curPage, String sortingOrder, CategoryInfoRequestDto categoryInfoRequestDto) {
		Pageable pageable = PageRequest.of(curPage, SIZE_PER_PAGE);
		Sort sort;
		
		switch(sortingOrder) {
		case "최신순": sort = Sort.by(Sort.Direction.DESC, "registerDate"); break;
		case "인기순": sort = Sort.by(Sort.Direction.DESC, "rate"); break;
		case "판매순": sort = Sort.by(Sort.Direction.DESC, "salesVolume"); break;
		case "조회순": sort = Sort.by(Sort.Direction.DESC, "view"); break;
		case "낮은가격순": sort = Sort.by(Sort.Direction.ASC, "price"); break;
		case "높은가격순": sort = Sort.by(Sort.Direction.DESC, "price"); break;
		default: throw new RuntimeException();
		}
		
		return notebookRepository.customFindNotebooksByCategory(pageable, sort, categoryInfoRequestDto);
	}
	
	public Page<NotebookDto.ListResponse> getMyPicks(int curPage, String customerId) {
		Pageable pageable = PageRequest.of(curPage, SIZE_PER_PAGE);
		
		Member customer = memberRepository.findById(customerId).get();
		List<String> notebookIds = customer.getMyPicks();
		
		return notebookRepository.customFindNotebooksByNotebookIds(pageable, notebookIds);
	}
}
