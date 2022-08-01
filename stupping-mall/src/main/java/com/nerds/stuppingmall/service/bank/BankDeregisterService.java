package com.nerds.stuppingmall.service.bank;

import com.nerds.stuppingmall.domain.Bank;
import com.nerds.stuppingmall.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankDeregisterService {
	final BankRepository bankRepository;
	
	public void removeBank(String id) {
		bankRepository.deleteById(id);
	}
}
