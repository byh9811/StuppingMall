package com.nerds.stuppingmall.service.tip;

import com.nerds.stuppingmall.domain.Tip;
import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.repository.TipRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipModifyService {
	TipRepository knowhowRepository;

	public void updateKnowhow(List<Tip> newTips) {
		for(Tip newTip : newTips) {
			Tip tip = new Tip();
			tip.setPurpose(newTip.getPurpose());
			tip.setExplanation1(newTip.getExplanation1());
			tip.setExplanation2(newTip.getExplanation2());
			tip.setExplanation3(newTip.getExplanation3());

			knowhowRepository.save(tip);
		}
	}
}
