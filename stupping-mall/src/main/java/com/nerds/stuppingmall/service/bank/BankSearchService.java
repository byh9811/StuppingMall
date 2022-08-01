package com.nerds.stuppingmall.service.bank;

import com.nerds.stuppingmall.domain.Bank;
import com.nerds.stuppingmall.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankSearchService {
	final BankRepository bankRepository;
	
	public List<Bank> getBankList() {
		return bankRepository.findAll();
	}
}
