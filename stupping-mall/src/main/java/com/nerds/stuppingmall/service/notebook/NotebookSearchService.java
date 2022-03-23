package com.nerds.stuppingmall.service.notebook;

import java.util.ArrayList;
import java.util.List;

import com.nerds.stuppingmall.dto.NotebookResponseBasicDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.domain.Notebook;
import com.nerds.stuppingmall.dto.NotebookInfoRequestDto;
import com.nerds.stuppingmall.dto.NotebookInfoResponseDto;
import com.nerds.stuppingmall.dto.NotebookInfoResponseSimpleDto;
import com.nerds.stuppingmall.enumerate.Usage;
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
	
	public List<NotebookInfoResponseSimpleDto> getRecent8Notebooks() {
		List<Notebook> notebooks = notebookRepository.findBy(PageRequest.of(0, 8));
		List<NotebookInfoResponseSimpleDto> notebookDtos = new ArrayList<>();

		for(Notebook notebook: notebooks) {
			notebookDtos.add(NotebookInfoResponseSimpleDto.builder()
					.name(notebook.getName())
					.img(notebook.getImgs().get(0))
					.price(notebook.getPrice())
					.cpuName(notebook.getCpuName())
					.weight(notebook.getWeight())
					.screenSize(notebook.getScreenSize())
					.ramSize(notebook.getRamSize())
					.build());
		}

		return notebookDtos;
	}
	
	public Page<NotebookResponseBasicDto> getMyNotebook(int curPage, String sortingOrder, String supplierId) {
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

	public Page<NotebookInfoResponseDto> findNotebooksBySupplierId(int curPage, String sortingOrder, String supplierId) {
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

	public Page<NotebookResponseBasicDto> findNotebookBasicDtosByName(int curPage, String sortingOrder, String name) {
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

	public Page<NotebookInfoResponseDto> findNotebooksByName(int curPage, String sortingOrder, String name) {
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

	public Page<NotebookInfoResponseDto> findNotebooksByCategory(int curPage, String sortingOrder, NotebookInfoRequestDto notebookInfoRequestDto) {
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
		
		return notebookRepository.customFindNotebooksByCategory(pageable, sort, notebookInfoRequestDto);
	}
	
	public Page<NotebookInfoResponseDto> getMyPicks(int curPage, String customerId) {
		Pageable pageable = PageRequest.of(curPage, SIZE_PER_PAGE);
		
		Member customer = memberRepository.findById(customerId).get();
		List<String> notebookIds = customer.getMyPicks();
		
		return notebookRepository.customFindNotebooksByNotebookIds(pageable, notebookIds);
	}
}
