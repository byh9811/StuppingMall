package com.nerds.stuppingmall.controller.bank;

import com.nerds.stuppingmall.service.bank.BankDeregisterService;
import com.nerds.stuppingmall.service.bank.BankModifyService;
import com.nerds.stuppingmall.service.bank.BankRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminBankController {
	final BankRegisterService bankRegisterService;
	final BankDeregisterService bankDeregisterService;
	final BankModifyService bankModifyService;

	@PostMapping("/bank/{name}")
	public String addBank(@PathVariable("name") String name) {
		bankRegisterService.addBank(name);
		return "redirect:/main";
	}

	@PutMapping("/bank/{id}/{name}")
	public String updateBank(@PathVariable("id") String id, @PathVariable("name") String name) {
		bankModifyService.updateBankName(id, name);
		return "redirect:/main";
	}

	@DeleteMapping("/bank/{id}")
	public String deleteBank(@PathVariable("id") String id) {
		bankDeregisterService.removeBank(id);
		return "redirect:/main";
	}
}