package com.nerds.stuppingmall.service;

import java.sql.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.repository.MemberRepository;

@Service
public class MemberService {
	@Autowired
	MemberRepository memberRepository;
	
	public List<Member> findAllMember() {
		return memberRepository.findAll();
	}
	
	public void insertMember(Member m) {
		memberRepository.save(m);
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
}
