package com.nerds.stuppingmall.repository;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.service.order.OrderSearchService;

@ExtendWith(MockitoExtension.class)
public class OrderSearchServiceTest {
	@InjectMocks
	OrderSearchService orderSearchService;
	
	@Test
	public void read() {
		Optional<Member> m = memberRepository.findById("ㅠㅛㅗ135700");
		
//		m.ifPresent(member -> {
//			System.out.println("selected member: " + member.getName());
//		});
		
		if(m.isPresent()) {
			System.out.println(m.get().getName());
		}
		else {
			throw new NoSuchElementException("해당 ID는 존재하지 않습니다!!");
		}
	}
}
