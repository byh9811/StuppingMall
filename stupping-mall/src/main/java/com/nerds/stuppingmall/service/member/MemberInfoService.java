package com.nerds.stuppingmall.service.member;

import java.util.List;
import java.util.Optional;

import com.nerds.stuppingmall.service.email.EmailSendService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberInfoService {
	final MemberRepository memberRepository;

	public String findMemberId(String email) {
		Optional<Member> memberWrapper = memberRepository.findByEmail(email);
		Member member = memberWrapper.get();
		String userId = null;
		for(Member member: members) {
			if(member.getPhoneNum().equals(phoneNum))
				userId = member.get_id();
		}
		return userId;
	}
}
