package com.nerds.stuppingmall.service.member;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.repository.member.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberDeregisterService {
	final MemberRepository memberRepository;

	public Member removeMember(String id) {
		Optional<Member> m = memberRepository.findById(id);
		if(!m.isPresent())
			throw new NoSuchElementException("해당 아이디가 존재하지 않습니다!!");

		Member member = m.get();
		memberRepository.delete(member);
		return member;
	}
}
