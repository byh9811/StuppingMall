package com.nerds.stuppingmall.service.member;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberDetailsService {
	final MemberRepository memberRepository;
	
	public Member findMemberById(String id) {
		return memberRepository.findById(id).get();
	}
	
	public List<Member> findAllMember() {
		List<Member> members = memberRepository.findAll();
		for(Member member: members)
			member.setPassword(null);
		return members;
	}
}
