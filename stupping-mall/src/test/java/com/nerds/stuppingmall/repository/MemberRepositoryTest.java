package com.nerds.stuppingmall.repository;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nerds.stuppingmall.StuppingMallApplicationTests;
import com.nerds.stuppingmall.domain.Member;

public class MemberRepositoryTest extends StuppingMallApplicationTests {
	@Autowired
	MemberRepository memberRepository;
	
	@Test
	public void read() {
		Optional<Member> m = memberRepository.findByUserId("ㅠㅛㅗ135700");
		
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
