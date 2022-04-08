package com.nerds.stuppingmall.service.introduction;

import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.domain.Introduction;
import com.nerds.stuppingmall.repository.IntroductionRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IntroductionModifyService {
	IntroductionRepository introductionRepository;
	
	public void updateIntroduction(List<Introduction> newIntroductions) {
		for(Introduction newIntroduction: newIntroductions) {
			Introduction introduction = new Introduction();
			introduction.setTitle(newIntroduction.getTitle());
			introduction.setContent(newIntroduction.getContent());

			introductionRepository.save(introduction);
		}
	}
}
