package com.nerds.stuppingmall.service.notebook;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<NotebookInfoResponseDto> getRecent8Notebooks() {
		List<Notebook> notebooks = notebookRepository.findBy(PageRequest.of(0, 8));
		List<NotebookInfoResponseDto> notebookDtos = new ArrayList<>();

		for(Notebook notebook: notebooks) {
			notebookDtos.add(NotebookInfoResponseDto.builder()
					.name(notebook.getName())
					.supplierName(notebook.getSupplierId())
					.registerDate(notebook.getRegisterDate())
					.img(notebook.getImg())
					.price(notebook.getPrice())
					.view(notebook.getView())
					.rate(notebook.getRate())
					.salesVolume(notebook.getSalesVolume())
					.cpuName(notebook.getCpuName())
					.gpuName(notebook.getGpuName())
					.weight(notebook.getWeight())
					.screenSize(notebook.getScreenSize())
					.ramSize(notebook.getRamSize())
					.ssdSize(notebook.getSsdSize())
					.hddSize(notebook.getHddSize())
					.batterySize(notebook.getBatterySize())
					.usage(Usage.valueOf(notebook.getUsage()))
					.build());
		}
		
		return notebookDtos;
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
