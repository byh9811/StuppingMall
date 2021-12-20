package com.nerds.stuppingmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.repository.MemberRepository;

@Transactional
public class MemberService {
	@Autowired
	MemberRepository memberRepository;
	
	public List<Member> findAllMember() {
		return memberRepository.findAll();
	}
}
