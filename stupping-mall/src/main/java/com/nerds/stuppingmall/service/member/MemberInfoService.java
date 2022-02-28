package com.nerds.stuppingmall.service.member;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberInfoService {
	final MemberRepository memberRepository;

	public String findMemberId(String name, String phoneNum) {
		List<Member> members = memberRepository.findByName(name);
		String userId = null;
		for(Member member: members) {
			if(member.getPhoneNum().equals(phoneNum))
				userId = member.get_id();
		}
		return userId;
	}
}
