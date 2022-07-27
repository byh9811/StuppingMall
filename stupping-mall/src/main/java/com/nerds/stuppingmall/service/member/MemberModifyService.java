package com.nerds.stuppingmall.service.member;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.regex.Pattern;

import com.nerds.stuppingmall.domain.Account;
import com.nerds.stuppingmall.domain.Customer;
import com.nerds.stuppingmall.dto.CustomerMyPageModifyRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.repository.member.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberModifyService {
	final MemberRepository memberRepository;
	final BCryptPasswordEncoder pwdEncoder;

	public Customer updateInfo(String email, CustomerMyPageModifyRequest newInfo) {
		Customer customer = memberRepository.findCustomerByEmail(email);
		customer.setName(newInfo.getName());
		customer.setPhoneNum(newInfo.getPhoneNum());
		customer.setAddress(new Member.Address(newInfo.getBaseAddress(), newInfo.getDetailAddress()));
		customer.setAccount(new Account(newInfo.getBank(), newInfo.getAccountNumber()));

		memberRepository.save(customer);

		return customer;
	}

	public Member updatePassword(String email, String password) {
		Optional<Member> memberWrapper = memberRepository.findById(email);
		if(!memberWrapper.isPresent())
			throw new NoSuchElementException("해당 아이디가 존재하지 않습니다!!");
		
		Member member = memberWrapper.get();
		member.setPassword(pwdEncoder.encode(password));
		return memberRepository.save(member);
	}

	public Member updateBalance(String email, int money) {
		Optional<Member> memberWrapper = memberRepository.findById(email);
		Member member = memberWrapper.get();
		// 페이 서비스 작동 성공
		member.setBalance(member.getBalance() + money);
		return memberRepository.save(member);
	}
	
	public Member addMyPick(String email, String notebookId) {
		Customer customer = memberRepository.findCustomerByEmail(email);
		List<String> myPicks = customer.getMyPicks();
		myPicks.add(notebookId);
		customer.setMyPicks(myPicks);
		return memberRepository.save(customer);
	}
	
	public Member removeMyPick(String email, String notebookId) {
		Customer customer = memberRepository.findCustomerByEmail(email);
		List<String> myPicks = customer.getMyPicks();
		myPicks.remove(notebookId);
		customer.setMyPicks(myPicks);
		return memberRepository.save(customer);
	}
}
