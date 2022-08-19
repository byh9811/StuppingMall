package com.nerds.stuppingmall.service.member;

import com.nerds.stuppingmall.domain.Customer;
import com.nerds.stuppingmall.domain.Supplier;
import com.nerds.stuppingmall.dto.CustomerSignUpRequestDto;
import com.nerds.stuppingmall.dto.SupplierSignUpRequestDto;
import com.nerds.stuppingmall.repository.member.CustomerRepository;
import com.nerds.stuppingmall.repository.member.CustomizedSupplierRepository;
import com.nerds.stuppingmall.repository.member.SupplierRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberRegisterService {
	final CustomerRepository customerRepository;
	final SupplierRepository supplierRepository;
	final BCryptPasswordEncoder pwdEncoder;
	
	public Customer addCustomer(CustomerSignUpRequestDto customerSignUpRequestDto) {
		customerSignUpRequestDto.setPassword(pwdEncoder.encode(customerSignUpRequestDto.getPassword()));
		return customerRepository.save(customerSignUpRequestDto.toDomain());
	}

	public Supplier addSupplier(SupplierSignUpRequestDto supplierSignUpRequestDto) {
		supplierSignUpRequestDto.setPassword(pwdEncoder.encode(supplierSignUpRequestDto.getPassword()));
		return supplierRepository.save(supplierSignUpRequestDto.toDomain());
	}
}
