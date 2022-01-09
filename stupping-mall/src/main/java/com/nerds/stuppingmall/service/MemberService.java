package com.nerds.stuppingmall.service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.dto.MemberDto;
import com.nerds.stuppingmall.repository.MemberRepository;

@Service
public class MemberService implements UserDetailsService {
	@Autowired
	MemberRepository memberRepository;
	
	public List<Member> findAllMember() {
		return memberRepository.findAll();
	}
	
	public void insertMember(MemberDto memberDto) {
		BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
		memberDto.setPassword(pwdEncoder.encode(memberDto.getPassword()));
		memberRepository.save(memberDto.toDomain());
	}
	
	public void updatePassword(String userId, String password) {
		Optional<Member> m = memberRepository.findByUserId(userId);
		if(!m.isPresent())
			throw new NoSuchElementException("해당 아이디가 존재하지 않습니다!!");
		
		Member member = m.get();
		member.setPassword(password);
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

	@Override
	public UserDetails loadUserByUsername(String username) {
		Optional<Member> memberWrapper = memberRepository.findByUserId(username);
		if(!memberWrapper.isPresent())
			throw new NoSuchElementException("해당 아이디가 존재하지 않습니다!!");

		Member member = memberWrapper.get();
		
		HashSet<GrantedAuthority> authorities = new HashSet<>();
		
		if("admin".equals(username)) {
			authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
		}
		else {
			authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
		}
		
		return new User(member.getUserId(), member.getPassword(), authorities);
	}
}
