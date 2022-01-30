package com.nerds.stuppingmall.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.dto.MemberDto;
import com.nerds.stuppingmall.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final BCryptPasswordEncoder pwdEncoder;
	private final MemberRepository memberRepository;
	
	public Optional<Member> findByUserId(String userId) {
		return memberRepository.findByUserId(userId);
	}
	
	public List<MemberDto> findAllMember() {
		List<MemberDto> list = new ArrayList<>();
		for(Member elem: memberRepository.findAll())
			list.add(new MemberDto(elem));
		return list;
	}
	
	public MemberDto insertMember(MemberDto memberDto) {
		memberDto.setPassword(pwdEncoder.encode(memberDto.getPassword()));
		memberRepository.save(memberDto.toDomain());
		return memberDto;
	}
	
	public void updatePassword(String userId, String password) {
		Optional<Member> memberWrapper = memberRepository.findByUserId(userId);
		if(!memberWrapper.isPresent())
			throw new NoSuchElementException("해당 아이디가 존재하지 않습니다!!");
		
		Member member = memberWrapper.get();
		member.setPassword(pwdEncoder.encode(password));
		memberRepository.save(member);
	}
	
	public String findUserId(String name, String phoneNum) {
		List<Member> members = memberRepository.findByName(name);
		String userId = null;
		for(Member m: members) {
			if(m.getPhoneNum().equals(phoneNum))
				userId = m.getUserId();
		}
		return userId;
	}
	
	public String findPassword(String userId, String phoneNum) {
		Optional<Member> m = memberRepository.findByUserId(userId);
		if(!m.isPresent())
			throw new NoSuchElementException("해당 아이디가 존재하지 않습니다!!");
		
		Member member = m.get();
		if(member.getPhoneNum().equals(phoneNum))
			return member.getPassword();
		else
			throw new NoSuchElementException("해당 전화번호가 존재하지 않습니다!!");
	}
	
	public void deleteUser(String userId, String password) {
		Optional<Member> m = memberRepository.findByUserId(userId);
		if(!m.isPresent())
			throw new NoSuchElementException("해당 아이디가 존재하지 않습니다!!");

		Member member = m.get();
		if(member.getPassword().equals(password))
			memberRepository.delete(member);
		else
			throw new NoSuchElementException("비밀번호가 틀렸습니다!!");
	}
}
