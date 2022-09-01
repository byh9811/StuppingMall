package com.nerds.stuppingmall.service.order;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.domain.Notebook;
import com.nerds.stuppingmall.domain.Order;
import com.nerds.stuppingmall.repository.member.MemberRepository;
import com.nerds.stuppingmall.repository.notebook.NotebookRepository;
import com.nerds.stuppingmall.repository.order.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderRegisterService {
	final OrderRepository orderRepository;
	final NotebookRepository notebookRepository;
	final MemberRepository memberRepository;
	
	public Order addOrder(String id, String notebookId, int payment) {
		Notebook notebook = notebookRepository.findById(notebookId).get();
		Member member = memberRepository.findById(id).get();
		Member admin = memberRepository.findById("admin").get();
		
		int BalanceAfterPurchase = member.getBalance() - payment;
		if(BalanceAfterPurchase < 0)
			throw new RuntimeException("잔액 부족");
		
		Order order = Order.builder()
						.customerId(id)
						.supplierId(notebook.getSupplierId())
						.notebookId(notebookId)
						.status("주문완료")
						.payDate(LocalDate.now().toString())
						.deliveredDate(null)
						.payment(payment)
						.build();
		
		member.setBalance(BalanceAfterPurchase);
		admin.setBalance(admin.getBalance()+payment);
		notebookRepository.save(notebook);
		memberRepository.save(member);
		memberRepository.save(admin);
		return orderRepository.save(order);
	}
	
}
