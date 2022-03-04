package com.nerds.stuppingmall.service.introduction;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.domain.Introduction;
import com.nerds.stuppingmall.repository.IntroductionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IntroductionSearchService {
	IntroductionRepository introductionRepository;
	
	public List<Introduction> getAllIntroductions() {
		return introductionRepository.findAll();
	}
	
	public Introduction getIntroduction(int index) {
		return introductionRepository.findById(index).get();
	}
}
