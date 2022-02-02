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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.dto.Authentication;
import com.nerds.stuppingmall.dto.MemberSignUpRequestDto;
import com.nerds.stuppingmall.repository.MemberRepository;

@Service
public class MemberService {
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	BCryptPasswordEncoder pwdEncoder;
	
	public List<Member> findAllMember() {
		List<Member> members = memberRepository.findAll();
		for(Member member: members)
			member.setPassword(null);
		return members;
	}
	
	public Member insertMember(MemberSignUpRequestDto memberSignUpRequestDto) {
		memberSignUpRequestDto.setPassword(pwdEncoder.encode(memberSignUpRequestDto.getPassword()));
		Member member = memberSignUpRequestDto.toDomain();
		memberRepository.save(member);
		return member;
	}

	public Member banMember(String id) {
		Optional<Member> m = memberRepository.findById(id);
		if(!m.isPresent())
			throw new NoSuchElementException("해당 아이디가 존재하지 않습니다!!");

		Member member = m.get();
		memberRepository.delete(member);
		return member;
	}

	public Member leave(String id) {
		Optional<Member> m = memberRepository.findById(id);
		if(!m.isPresent())
			throw new NoSuchElementException("해당 아이디가 존재하지 않습니다!!");

		Member member = m.get();
		memberRepository.delete(member);
		return member;
	}

	public void updatePassword(String id, String password) {
		Optional<Member> memberWrapper = memberRepository.findById(id);
		if(!memberWrapper.isPresent())
			throw new NoSuchElementException("해당 아이디가 존재하지 않습니다!!");
		
		Member member = memberWrapper.get();
		member.setPassword(pwdEncoder.encode(password));
		memberRepository.save(member);
	}
	
	public String findUserId(String name, String phoneNum) {
		List<Member> members = memberRepository.findByName(name);
		String userId = null;
		for(Member member: members) {
			if(member.getPhoneNum().equals(phoneNum))
				userId = member.get_id();
		}
		return userId;
	}

	public Member getMyPage(String id) {
		return memberRepository.findById(id).get();
	}
	
}
