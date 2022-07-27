package com.nerds.stuppingmall.repository;

import com.nerds.stuppingmall.StuppingMallApplication;
import com.nerds.stuppingmall.domain.Account;
import com.nerds.stuppingmall.domain.Customer;
import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.domain.Notebook;
import com.nerds.stuppingmall.dto.NotebookDto;
import com.nerds.stuppingmall.repository.member.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = StuppingMallApplication.class)
@DirtiesContext
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
public class MemberRepositoryTest {
	@Autowired
	MemberRepository memberRepository;

	@Autowired
	BCryptPasswordEncoder pwdEncoder;

	@Test
	@DisplayName("개인회원 저장")
	public void saveCustomer() {
		// given
		List<String> myPicks = List.of(new String[]{"notebookId1, notebookId2"});
		List<String> recentFinds = List.of(new String[]{"notebookId3, notebookId4"});

		Customer customer = Customer.builder()
				.email("byh9811@gachon.ac.kr")
				.password(pwdEncoder.encode("byh1004"))
				.name("배용현")
				.role("ROLE_CUSTOMER")
				.account(new Account("신한", "110-209-778808"))
				.balance(0)
				.address(new Member.Address("서울시 노원구 초안산로 1길 18", "211동 404호"))
				.phoneNum("010-7185-2569")
				.birth("1998-11-28")
				.man(true)
				.myPicks(myPicks)
				.recentFinds(recentFinds)
				.build();

		// when
		Customer savedCustomer = memberRepository.save(customer);

		// then
		assertEquals(customer, savedCustomer);
	}
	
//	@Test
//	public void readNotebook() {
//		// given
//		String supplierId = "Samsung";
//
//		// when
//		List<Notebook> notebooks = notebookRepository.findBy(PageRequest.of(0, 8));
//		List<NotebookDto.ListResponse> notebookDtos = new ArrayList<>();
//
//		for(Notebook notebook: notebooks)
//			notebookDtos.add(new NotebookDto.ListResponse(notebook));
//
//		// then
//		assertEquals(8, notebooks.size());
//		assertNotNull(notebookDtos.get(0).getImg());
//	}
}
