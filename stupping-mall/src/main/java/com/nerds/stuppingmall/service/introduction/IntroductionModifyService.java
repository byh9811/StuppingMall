package com.nerds.stuppingmall.service.introduction;

import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.domain.Introduction;
import com.nerds.stuppingmall.repository.IntroductionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IntroductionModifyService {
	IntroductionRepository introductionRepository;
	
	public Introduction updateIntroduction(int index, Introduction newIntroduction) {
		Introduction introduction = introductionRepository.findById(index).get();
		
		introduction.setTitle(newIntroduction.getTitle());
		introduction.setContent(newIntroduction.getContent());
		
		return introductionRepository.save(introduction);
	}
}
