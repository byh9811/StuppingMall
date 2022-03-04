package com.nerds.stuppingmall.service.knowhow;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.domain.Knowhow;
import com.nerds.stuppingmall.repository.KnowhowRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KnowhowSearchService {
	KnowhowRepository knowhowRepository;
	
	public List<Knowhow> getAllKnowhows() {
		return knowhowRepository.findAll();
	}
	
	public Knowhow getKnowhow(int index) {
		return knowhowRepository.findById(index).get();
	}
}
