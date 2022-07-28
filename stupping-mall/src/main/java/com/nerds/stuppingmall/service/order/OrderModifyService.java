package com.nerds.stuppingmall.service.order;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Service;

import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.domain.Notebook;
import com.nerds.stuppingmall.domain.Order;
import com.nerds.stuppingmall.repository.member.MemberRepository;
import com.nerds.stuppingmall.repository.notebook.NotebookRepository;
import com.nerds.stuppingmall.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderModifyService {
	final OrderRepository orderRepository;
	final MemberRepository memberRepository;
	final NotebookRepository notebookRepository;
	
	public Order acceptOrder(String orderId) {
		Order order = orderRepository.findById(orderId).get();
		order.setStatus("상품 준비중");
		return orderRepository.save(order);
	}
	
	public Order denyOrder(String orderId) {
		Order order = orderRepository.findById(orderId).get();
		Member customer = memberRepository.findById(order.getCustomerId()).get();
		Member admin = memberRepository.findById("admin").get();
		
		order.setStatus("주문 취소됨");
		customer.setBalance(customer.getBalance() + order.getPayment());
		admin.setBalance(admin.getBalance() - order.getPayment());
		
		memberRepository.save(admin);
		memberRepository.save(customer);
		return orderRepository.save(order);
	}
	
	public Order startDelivery(String orderId) {
		Order order = orderRepository.findById(orderId).get();
		
		order.setStatus("배송 시작");
		return orderRepository.save(order);
	}
	
	public Order finishDelivery(String orderId) {
		Order order = orderRepository.findById(orderId).get();
		
		order.setDeliveredDate(LocalDate.now().toString());
		order.setStatus("배송 완료");
		return orderRepository.save(order);
	}
	
	public Order completeOrder(String orderId) {
		Order order = orderRepository.findById(orderId).get();
		Member supplier = memberRepository.findById(order.getSupplierId()).get();
		Member admin = memberRepository.findById("admin").get();
		Notebook notebook = notebookRepository.findById(order.getNotebookId()).get();
		
		if(Period.between(LocalDate.parse(order.getDeliveredDate()), LocalDate.now()).getDays()<=7 ||
				LocalDate.parse(order.getDeliveredDate()).isBefore(LocalDate.parse(order.getPayDate()))) {
			throw new RuntimeException("아직 구매결정이 되지 않았습니다.");
		}
		
		order.setStatus("구매 완료");
		admin.setBalance(admin.getBalance() - order.getPayment());
		supplier.setBalance(supplier.getBalance() + order.getPayment());
		notebook.setSalesVolume(notebook.getSalesVolume()+1);
		
		memberRepository.save(admin);
		memberRepository.save(supplier);
		notebookRepository.save(notebook);
		return orderRepository.save(order);
	}
	
	public Order makeObjection(String orderId) {
		Order order = orderRepository.findById(orderId).get();
		
		order.setStatus("이의신청 진행중");
		order.setPayDate(LocalDate.now().toString());
		
		return orderRepository.save(order);
	}
	
	public Order acceptObjection(String orderId) {
		Order order = orderRepository.findById(orderId).get();
		
		order.setStatus("이의신청 승인됨");
		order.setPayDate(LocalDate.now().toString());
		
		return orderRepository.save(order);
	}
	
	public Order denyObjection(String orderId, String reason) {
		Order order = orderRepository.findById(orderId).get();
		
		order.setStatus("이의신청 반려됨(사유: " + reason + ")");
		
		return orderRepository.save(order);
	}
}
