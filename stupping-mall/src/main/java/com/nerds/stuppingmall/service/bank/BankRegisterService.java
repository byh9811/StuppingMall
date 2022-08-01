package com.nerds.stuppingmall.service.bank;

import com.nerds.stuppingmall.domain.Bank;
import com.nerds.stuppingmall.domain.Customer;
import com.nerds.stuppingmall.dto.CustomerSignUpRequestDto;
import com.nerds.stuppingmall.repository.BankRepository;
import com.nerds.stuppingmall.repository.member.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankRegisterService {
	final BankRepository bankRepository;
	
	public Bank addBank(String name) {
		return bankRepository.save(Bank.builder().name(name).build());
	}
}
