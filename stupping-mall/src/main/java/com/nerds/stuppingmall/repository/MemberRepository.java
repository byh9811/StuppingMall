package com.nerds.stuppingmall.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nerds.stuppingmall.domain.Member;

public interface MemberRepository extends MongoRepository<Member, Long> {
	Optional<Member> findByUserId(String userId);
	List<Member> findByName(String name);
}
