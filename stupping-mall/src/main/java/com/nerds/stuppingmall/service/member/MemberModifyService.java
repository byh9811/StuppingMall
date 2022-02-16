package com.nerds.stuppingmall.service.member;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.dto.Authentication;
import com.nerds.stuppingmall.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberModifyService {
	final MemberRepository memberRepository;
	final BCryptPasswordEncoder pwdEncoder;
	
	public Member updatePassword(String id, String password) {
		Optional<Member> memberWrapper = memberRepository.findById(id);
		if(!memberWrapper.isPresent())
			throw new NoSuchElementException("해당 아이디가 존재하지 않습니다!!");
		
		Member member = memberWrapper.get();
		member.setPassword(pwdEncoder.encode(password));
		return memberRepository.save(member);
	}

	public Member updateBalance(String id, int money) {
		Optional<Member> memberWrapper = memberRepository.findById(id);
		Member member = memberWrapper.get();
		// 페이 서비스 작동 성공
		member.setBalance(member.getBalance() + money);
		return memberRepository.save(member);
	}
}
