package com.nerds.stuppingmall.service.member;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.regex.Pattern;

import com.nerds.stuppingmall.domain.Account;
import com.nerds.stuppingmall.dto.AccessibleInfoRequestDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberModifyService {
	final MemberRepository memberRepository;
	final BCryptPasswordEncoder pwdEncoder;

	public Member updateInfo(String id, AccessibleInfoRequestDto newInfo) {
		Member member = memberRepository.findById(id).get();
		member.setName(newInfo.getName());
		String newPassword = newInfo.getPassword();
		if(newPassword.length()!=0) {
			if(Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,20}$", newPassword))
				member.setPassword(newInfo.getPassword());
			else throw new NoSuchElementException("패턴이 올바르지 않습니다.");
		}
		member.setEmail(newInfo.getEmail());
		member.setPhoneNum(newInfo.getPhoneNum());
		member.setBirth(newInfo.getBirth());
		member.setAccount(new Account(newInfo.getBank(), newInfo.getBankNumber()));
		memberRepository.save(member);

		return member;
	}

	public Member updatePassword(String id, String password) {
		Optional<Member> memberWrapper = memberRepository.findById(id);
		if(!memberWrapper.isPresent())
			throw new NoSuchElementException("해당 아이디가 존재하지 않습니다!!");
		
		Member member = memberWrapper.get();
		member.setPassword(pwdEncoder.encode(password));
		return memberRepository.save(member);
	}

	public Member updateBalance(String id, int money) {
		Optional<Member> memberWrapper = memberRepository.findById(id);
		Member member = memberWrapper.get();
		// 페이 서비스 작동 성공
		member.setBalance(member.getBalance() + money);
		return memberRepository.save(member);
	}
	
	public Member addMyPick(String customerId, String notebookId) {
		Member member = memberRepository.findById(customerId).get();
		List<String> myPicks = member.getMyPicks();
		myPicks.add(notebookId);
		member.setMyPicks(myPicks);
		return memberRepository.save(member);
	}
	
	public Member removeMyPick(String customerId, String notebookId) {
		Member member = memberRepository.findById(customerId).get();
		List<String> myPicks = member.getMyPicks();
		myPicks.remove(notebookId);
		member.setMyPicks(myPicks);
		return memberRepository.save(member);
	}
}
