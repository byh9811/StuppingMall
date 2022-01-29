package com.nerds.stuppingmall.service;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.dto.MemberDto;
import com.nerds.stuppingmall.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {
	private final MemberRepository memberRepository;
	
	@Override
	public MemberDto loadUserByUsername(String userId) {
		Optional<Member> memberWrapper = memberRepository.findByUserId(userId);
		if(!memberWrapper.isPresent())
			throw new NoSuchElementException("해당 아이디가 존재하지 않습니다!!");

		Member member = memberWrapper.get();
		
		HashSet<GrantedAuthority> authorities = new HashSet<>();
		
		String role = member.getRole();
		switch(role) {
		case "ADMIN": authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue())); break;
		case "SELLER": authorities.add(new SimpleGrantedAuthority(Role.SELLER.getValue())); break;
		case "MEMBER": authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue())); break;
		default: throw new NoSuchElementException("부적절한 역할입니다!!");
		}
		
		MemberDto memberDto = new MemberDto(member);
		memberDto.setAuthorities(authorities);
		
		return memberDto;
	}
}
