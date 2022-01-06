package com.nerds.stuppingmall.service;

import java.sql.Date;
import java.util.List;

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
		Member m = memberRepository.findByUserId(userId);
		m.setPassword(password);
		memberRepository.save(m);
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
		Member m = memberRepository.findByUserId(userId);
		if(m.getPhoneNum().equals(phoneNum))
			return m.getPassword();
		else
			return null;
	}
	
	public void deleteUser(String userId, String password) {
		Member m = memberRepository.findByUserId(userId);
		if(m.getPassword().equals(password))
			memberRepository.delete(m);
	}
}
