package com.nerds.stuppingmall.service.tip;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.domain.Tip;
import com.nerds.stuppingmall.repository.TipRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TipSearchService {
	final TipRepository tipRepository;
	
	public List<Tip> getAllTips() {
		return tipRepository.findAll();
	}
	
	public Tip getTip(int index) {
		return tipRepository.findById(index).get();
	}
}
