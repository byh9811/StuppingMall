package com.nerds.stuppingmall.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.repository.MemberRepository;

@Service
public class MemberService {
	@Autowired
	MemberRepository memberRepository;
	
	public List<Member> findAllMember() {
		return memberRepository.findAll();
	}
	
	public void insertMember(Member m) {
//		Member m = new Member();
//		m.setPassword("987654321");
//		m.setName("김지성");
//		m.setEmail("js4012@naver.com");
//		m.setPhoneNum("010-9287-6446");
//		m.setBirth(Date.valueOf("1998-04-11"));
//		m.setMan(true);
		memberRepository.save(m);
	}
}
