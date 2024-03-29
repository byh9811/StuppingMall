package com.nerds.stuppingmall.service.member;

import java.util.List;

import com.nerds.stuppingmall.domain.Account;
import com.nerds.stuppingmall.domain.Customer;
import com.nerds.stuppingmall.domain.Supplier;
import com.nerds.stuppingmall.dto.CustomerMyPageModifyRequest;
import com.nerds.stuppingmall.repository.member.CustomerRepository;
import com.nerds.stuppingmall.repository.member.CustomizedSupplierRepository;
import com.nerds.stuppingmall.repository.member.SupplierRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.repository.member.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberModifyService {
	final MemberRepository memberRepository;
	final CustomerRepository customerRepository;
	final SupplierRepository supplierRepository;
	final BCryptPasswordEncoder pwdEncoder;

	public Customer updateInfo(String email, CustomerMyPageModifyRequest newInfo) {
		Customer customer = customerRepository.findCustomerByEmail(email);
		customer.setName(newInfo.getName());
		customer.setPhoneNum(newInfo.getPhoneNum());
		customer.setAddress(new Customer.Address(newInfo.getBaseAddress(), newInfo.getDetailAddress()));
		customer.setAccount(new Account(newInfo.getBank(), newInfo.getAccountNumber()));

		memberRepository.save(customer);

		return customer;
	}

	public Customer updatePassword(String email, String password) {
		Customer customer = customerRepository.findByEmail(email);
		customer.setPassword(pwdEncoder.encode(password));
		return customerRepository.save(customer);
	}

	public Customer updateBalance(String email, int money) {
		Customer customer = customerRepository.findByEmail(email);
		// 페이 서비스 작동 성공
		customer.setBalance(customer.getBalance() + money);
		return memberRepository.save(customer);
	}

	public Customer addMyPick(String email, String notebookId) {
		Customer customer = customerRepository.findCustomerByEmail(email);
		List<String> myPicks = customer.getMyPicks();
		myPicks.add(notebookId);
		customer.setMyPicks(myPicks);
		return customerRepository.save(customer);
	}

	public Customer removeMyPick(String email, String notebookId) {
		Customer customer = customerRepository.findCustomerByEmail(email);
		List<String> myPicks = customer.getMyPicks();
		myPicks.remove(notebookId);
		customer.setMyPicks(myPicks);
		return customerRepository.save(customer);
	}

	public Supplier updateEnable(String email) {
		Supplier supplier = supplierRepository.findSupplierByEmail(email);
		supplier.setEnable(true);
		return supplierRepository.save(supplier);
	}
}