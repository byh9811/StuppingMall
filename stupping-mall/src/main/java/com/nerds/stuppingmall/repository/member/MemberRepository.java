package com.nerds.stuppingmall.repository.member;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nerds.stuppingmall.domain.Member;

public interface MemberRepository extends MongoRepository<Member, String>, CustomerRepository {
	List<Member> findByName(String name);
	Optional<Member> findByEmail(String email);
}
