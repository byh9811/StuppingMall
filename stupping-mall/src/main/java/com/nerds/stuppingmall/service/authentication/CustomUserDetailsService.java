package com.nerds.stuppingmall.service.authentication;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.dto.Authentication;
import com.nerds.stuppingmall.repository.member.MemberRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	MemberRepository memberRepository;
	
	@Override
	public Authentication loadUserByUsername(String id) {
		Optional<Member> memberWrapper = memberRepository.findById(id);
		if(!memberWrapper.isPresent())
			throw new NoSuchElementException("해당 아이디가 존재하지 않습니다!!");

		Member member = memberWrapper.get();
		
		HashSet<GrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority(member.getRole()));
		
		return Authentication.builder()
				.id(member.getEmail())
				.password(member.getPassword())
				.authorities(authorities)
				.build();
	}
}
