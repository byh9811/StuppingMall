package com.nerds.stuppingmall.service.knowhow;

import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.domain.Introduction;
import com.nerds.stuppingmall.domain.Knowhow;
import com.nerds.stuppingmall.repository.KnowhowRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KnowhowModifyService {
	KnowhowRepository knowhowRepository;
	
	public Knowhow updateKnowhow(int index, Knowhow newKnowhow) {
		Knowhow knowhow = knowhowRepository.findById(index).get();
		
		knowhow.setPurpose(newKnowhow.getPurpose());
		knowhow.setExplanation(newKnowhow.getExplanation());
		
		return knowhowRepository.save(knowhow);
	}
}
