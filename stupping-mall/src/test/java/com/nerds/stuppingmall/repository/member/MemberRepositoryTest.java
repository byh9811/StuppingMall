package com.nerds.stuppingmall.repository.member;

import com.nerds.stuppingmall.StuppingMallApplication;
import com.nerds.stuppingmall.domain.Account;
import com.nerds.stuppingmall.domain.Customer;
import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.repository.member.CustomizedCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = StuppingMallApplication.class)
@DirtiesContext
@DataMongoTest
public class MemberRepositoryTest {
	@Autowired
	CustomerRepository customerRepository;

//	@Autowired
//	BCryptPasswordEncoder pwdEncoder;

	@Test
	@DisplayName("개인회원 저장")
	public void saveCustomer() {
		// given
		List<String> myPicks = List.of(new String[]{"notebookId1, notebookId2"});
		List<String> recentFinds = List.of(new String[]{"notebookId3, notebookId4"});

		Customer customer = Customer.builder()
				.email("byh9811@gachon.ac.kr")
				.password("byh1004")
				.name("배용현")
				.role("ROLE_CUSTOMER")
				.account(new Account("신한", "110-209-778808"))
				.balance(0)
				.address(new Customer.Address("서울시 노원구 초안산로 1길 18", "211동 404호"))
				.phoneNum("010-7185-2569")
				.myPicks(myPicks)
				.recentFinds(recentFinds)
				.build();

		// when
		Customer savedCustomer = customerRepository.save(customer);

		// then
		assertEquals(customer, savedCustomer);
	}
	
	@Test
	public void readMember() {
		// given
		String email = "byh9811@gachon.ac.kr";

		// when
//		Customer member1 = customerRepository.findById(email).get();
		Customer member2 = customerRepository.findByEmail(email);
		Customer member3 = customerRepository.findCustomerByEmail(email);
		Customer customer = customerRepository.findCustomerByEmail(email);

		// then
//		assertEquals(member1.getEmail(), email);
		assertEquals(member2.getEmail(), email);
		assertEquals(member3.getEmail(), email);
		assertEquals(customer.getEmail(), email);
	}
}
