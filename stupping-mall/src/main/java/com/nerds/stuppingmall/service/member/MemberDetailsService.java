package com.nerds.stuppingmall.service.member;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.domain.Notebook;
import com.nerds.stuppingmall.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberDetailsService {
	final MemberRepository memberRepository;
	final MongoTemplate mongoTemplate;
	final int SIZE_PER_PAGE = 10;
	
	public Member findMemberById(String id) {
		return memberRepository.findById(id).get();
	}
	
	public Page<Member> findAllMembers(int curPage) {
		Pageable pageable = PageRequest.of(curPage, SIZE_PER_PAGE);
		Page<Member> members = memberRepository.findAll(pageable);
		for(Member member: members.getContent())
			member.setPassword(null);
		return members;
	}
	
	public Page<Member> findMembersByName(int curPage, String name) {
		Pageable pageable = PageRequest.of(curPage, SIZE_PER_PAGE);
		Query query = new Query();
		query.with(pageable);
		query.addCriteria(Criteria.where("name").regex(name));
		
		List<Member> members = mongoTemplate.find(query, Member.class, "members");
		for(Member member: members)
			member.setPassword(null);
		
		return PageableExecutionUtils.getPage(
				members, pageable,
				() -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), Member.class));
	}
}
