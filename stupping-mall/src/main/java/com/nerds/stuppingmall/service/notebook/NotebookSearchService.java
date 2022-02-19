package com.nerds.stuppingmall.service.notebook;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotebookSearchService {
	final MongoTemplate mongoTemplate;
	final MemberRepository memberRepository;
	final int SIZE_PER_PAGE = 10;
	
	public Page<NotebookInfoResponseDto> findNotebooksBySupplierId(int curPage, String supplierId) {
		Pageable pageable = PageRequest.of(curPage, SIZE_PER_PAGE);
		Query query = new Query();
		query.addCriteria(Criteria.where("supplierId").is(supplierId));
		query.with(pageable);
		
		List<Notebook> myNotebooks = mongoTemplate.find(query, Notebook.class, "notebooks");
		List<NotebookInfoResponseDto> notebookDtos = new ArrayList<>();
		String supplierName = memberRepository.findById(supplierId).get().getName();
		
		for(Notebook notebook: myNotebooks) {
			notebookDtos.add(NotebookInfoResponseDto.builder()
					.name(notebook.getName())
					.supplierName(supplierName)
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
		
		return PageableExecutionUtils.getPage(
				notebookDtos, pageable,
				() -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), Notebook.class));
	}
	
	public Page<NotebookInfoResponseDto> findNotebooksByName(int curPage, String name) {
		Pageable pageable = PageRequest.of(curPage, SIZE_PER_PAGE);
		Query query = new Query();
		query.addCriteria(Criteria.where("name").regex(name));
		query.with(pageable);
		
		List<Notebook> notebooks = mongoTemplate.find(query, Notebook.class, "notebooks");
		List<NotebookInfoResponseDto> notebookDtos = new ArrayList<>();
		for(Notebook notebook: notebooks) {
			notebookDtos.add(NotebookInfoResponseDto.builder()
					.name(notebook.getName())
					.supplierName(memberRepository.findById(notebook.getSupplierId()).get().getName())
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
		
		return PageableExecutionUtils.getPage(
				notebookDtos, pageable,
				() -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), Notebook.class));
	}

	public Page<NotebookInfoResponseDto> findNotebooksByCategory(int curPage, NotebookInfoRequestDto notebookInfoRequestDto) {
		Pageable pageable = PageRequest.of(curPage, SIZE_PER_PAGE);
		Query query = new Query();
		List<Criteria> outerCriterias = new ArrayList<>();
		// 이거 로직 바꿔야됨 supplierName -> supplierId
		if(!notebookInfoRequestDto.getSupplierNames().isEmpty()) {
			Criteria criteria = new Criteria();
			Criteria[] innerCriterias = new Criteria[notebookInfoRequestDto.getSupplierNames().size()];
			for(int i=0; i<notebookInfoRequestDto.getSupplierNames().size(); i++) {
				innerCriterias[i] = Criteria.where("supplierId").is(notebookInfoRequestDto.getSupplierNames().get(i));
			}
			outerCriterias.add(criteria.orOperator(innerCriterias));
		}
		if(!notebookInfoRequestDto.getCpuNames().isEmpty()) {
			Criteria criteria = new Criteria();
			Criteria[] innerCriterias = new Criteria[notebookInfoRequestDto.getCpuNames().size()];
			for(int i=0; i<notebookInfoRequestDto.getCpuNames().size(); i++) {
				innerCriterias[i] = Criteria.where("cpuName").is(notebookInfoRequestDto.getCpuNames().get(i));
			}
			outerCriterias.add(criteria.orOperator(innerCriterias));
		}
		if(!notebookInfoRequestDto.getGpuNames().isEmpty()) {
			Criteria criteria = new Criteria();
			Criteria[] innerCriterias = new Criteria[notebookInfoRequestDto.getGpuNames().size()];
			for(int i=0; i<notebookInfoRequestDto.getGpuNames().size(); i++) {
				innerCriterias[i] = Criteria.where("gpuName").is(notebookInfoRequestDto.getGpuNames().get(i));
			}
			outerCriterias.add(criteria.orOperator(innerCriterias));
		}
		if(!notebookInfoRequestDto.getRegisterYears().isEmpty()) {
			Criteria criteria = new Criteria();
			Criteria[] innerCriterias = new Criteria[notebookInfoRequestDto.getRegisterYears().size()];
			for(int i=0; i<notebookInfoRequestDto.getRegisterYears().size(); i++) {
				innerCriterias[i] = Criteria.where("manufactureDate").regex("("+notebookInfoRequestDto.getRegisterYears().get(i)+")+");
			}
			outerCriterias.add(criteria.orOperator(innerCriterias));
		}
		
		if(!outerCriterias.isEmpty()) {
			Criteria criteria = new Criteria();
			query.addCriteria(criteria.andOperator(outerCriterias));
			query.with(pageable);
		}
		
		List<Notebook> notebooks = mongoTemplate.find(query, Notebook.class, "notebooks");
		List<NotebookInfoResponseDto> notebookDtos = new ArrayList<>();
		
		for(Notebook notebook: notebooks) {
			notebookDtos.add(NotebookInfoResponseDto.builder()
					.name(notebook.getName())
					.supplierName(memberRepository.findById(notebook.getSupplierId()).get().getName())
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

		Page<NotebookInfoResponseDto> notebookPages = PageableExecutionUtils.getPage(notebookDtos, pageable, () -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), Notebook.class));
		return notebookPages;
	}
	
	public Page<NotebookInfoResponseDto> getMyPicks(int curPage, String customerId) {
		Member customer = memberRepository.findById(customerId).get();
		List<String> notebookIds = customer.getMyPicks();
		List<Criteria> criterias = new ArrayList<>();
		Pageable pageable = PageRequest.of(curPage, SIZE_PER_PAGE);
		Query query = new Query();
		for(String notebookId: notebookIds)
			criterias.add(Criteria.where("notebookId").is(notebookId));
		Criteria criteria = new Criteria();
		query.addCriteria(criteria.orOperator(criterias));
		query.with(pageable);
		
		List<Notebook> notebooks = mongoTemplate.find(query, Notebook.class, "notebooks");
		List<NotebookInfoResponseDto> notebookDtos = new ArrayList<>();
		for(Notebook notebook: notebooks) {
			notebookDtos.add(NotebookInfoResponseDto.builder()
					.name(notebook.getName())
					.supplierName(memberRepository.findById(notebook.getSupplierId()).get().getName())
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
		
		return PageableExecutionUtils.getPage(
				notebookDtos, pageable,
				() -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), Notebook.class));
	}
}
