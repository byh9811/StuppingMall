package com.nerds.stuppingmall.service.member;

import com.nerds.stuppingmall.domain.Customer;
import com.nerds.stuppingmall.dto.CustomerSignUpRequestDto;
import com.nerds.stuppingmall.repository.member.CustomerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.repository.member.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberRegisterService {
	final CustomerRepository customerRepository;
	final BCryptPasswordEncoder pwdEncoder;
	
	public Customer addCustomer(CustomerSignUpRequestDto customerSignUpRequestDto) {
		customerSignUpRequestDto.setPassword(pwdEncoder.encode(customerSignUpRequestDto.getPassword()));
		return customerRepository.save(customerSignUpRequestDto.toDomain());
	}
}
