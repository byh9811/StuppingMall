package com.nerds.stuppingmall.controller.bank;

import com.nerds.stuppingmall.domain.Bank;
import com.nerds.stuppingmall.service.bank.BankSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BankController {
	final BankSearchService bankSearchService;

	@GetMapping("/banks")
	public List<Bank> getBankList() {
		return bankSearchService.getBankList();
	}
}