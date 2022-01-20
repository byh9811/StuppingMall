package com.nerds.stuppingmall.service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.domain.Product;
import com.nerds.stuppingmall.dto.MemberDto;
import com.nerds.stuppingmall.dto.ProductDto;
import com.nerds.stuppingmall.repository.MemberRepository;
import com.nerds.stuppingmall.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	
	public ProductDto getProduct(String id) {
		Optional<Product> productWrapper = productRepository.findById(id);

		if(!productWrapper.isPresent())
			throw new NoSuchElementException("해당 아이디가 존재하지 않습니다!!");
		
		Product product = productWrapper.get();
		return new ProductDto(
				product.get_id(), product.getName(), product.getCategory(),
				product.getPrice(), product.getImg(), product.getSeller(),
				product.getView(), product.getRate(), product.getSalesVolume(), product.getDate());
	}
}
