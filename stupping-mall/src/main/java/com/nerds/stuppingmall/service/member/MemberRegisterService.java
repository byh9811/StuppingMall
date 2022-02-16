package com.nerds.stuppingmall.service.member;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.dto.MemberSignUpRequestDto;
import com.nerds.stuppingmall.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberRegisterService {
	final MemberRepository memberRepository;
	final BCryptPasswordEncoder pwdEncoder;
	
	public Member addMember(MemberSignUpRequestDto memberSignUpRequestDto) {
		memberSignUpRequestDto.setPassword(pwdEncoder.encode(memberSignUpRequestDto.getPassword()));
		Member member = memberSignUpRequestDto.toDomain();
		member.setBalance(0);
		memberRepository.save(member);
		return member;
	}
}
