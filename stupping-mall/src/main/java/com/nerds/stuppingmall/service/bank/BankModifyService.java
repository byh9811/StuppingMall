package com.nerds.stuppingmall.service.bank;

import com.nerds.stuppingmall.domain.Bank;
import com.nerds.stuppingmall.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankModifyService {
	final BankRepository bankRepository;
	
	public Bank updateBankName(String id, String name) {
		return bankRepository.save(Bank.builder()._id(id).name(name).build());
	}
}
