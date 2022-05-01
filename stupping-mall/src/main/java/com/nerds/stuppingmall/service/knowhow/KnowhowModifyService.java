package com.nerds.stuppingmall.service.knowhow;

import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.domain.Introduction;
import com.nerds.stuppingmall.domain.Knowhow;
import com.nerds.stuppingmall.repository.KnowhowRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KnowhowModifyService {
	KnowhowRepository knowhowRepository;

	public void updateKnowhow(List<Knowhow> newKnowhows) {
		for(Knowhow newKnowhow: newKnowhows) {
			Knowhow knowhow = new Knowhow();
			knowhow.setPurpose(newKnowhow.getPurpose());
			knowhow.setExplanation(newKnowhow.getExplanation());

			knowhowRepository.save(knowhow);
		}
	}
}
