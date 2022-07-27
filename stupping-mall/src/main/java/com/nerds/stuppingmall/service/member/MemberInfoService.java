package com.nerds.stuppingmall.service.member;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.repository.member.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberInfoService {
	final MemberRepository memberRepository;

	public String findMemberId(String email) {
		Optional<Member> memberWrapper = memberRepository.findByEmail(email);
		Member member = memberWrapper.get();
		return member.get_id();
	}
}
