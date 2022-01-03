package com.nerds.stuppingmall.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nerds.stuppingmall.domain.Member;

public interface MemberRepository extends MongoRepository<Member, Long> {
	Member findByUserId(String userId);
}
