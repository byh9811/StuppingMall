package com.nerds.stuppingmall.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
import com.nerds.stuppingmall.dto.ProductAddRequestDto;
import com.nerds.stuppingmall.dto.ProductDto;
import com.nerds.stuppingmall.repository.MemberRepository;
import com.nerds.stuppingmall.repository.ProductRepository;
import java.time.LocalDate;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	
	public Product getProduct(String id) {
		Optional<Product> productWrapper = productRepository.findById(id);

		if(!productWrapper.isPresent())
			throw new NoSuchElementException("해당 상품이 존재하지 않습니다!!");
		
		return productWrapper.get();
	}

	public String addProduct(String supplierId, ProductAddRequestDto productAddRequestDto) {
		String basePath = "C:\\img";
		String filePath = basePath + "/" + supplierId;
		String url = null;
		File folder = new File(filePath);
		
		if(!folder.exists())
			folder.mkdirs();
		
		if(!productAddRequestDto.getImgFile().isEmpty()) {
            // jpeg, png, gif 파일들만 받아서 처리
            String contentType = productAddRequestDto.getImgFile().getContentType();
            String originalFileExtension;
            
            // 확장자 명이 없으면 이 파일은 잘 못 된 것이다
            if (!contentType.isEmpty()) {
                if(contentType.contains("image/jpeg")){
                    originalFileExtension = ".jpg";
                }
                else if(contentType.contains("image/png")){
                    originalFileExtension = ".png";
                }
                else if(contentType.contains("image/gif")){
                    originalFileExtension = ".gif";
                }
                // 다른 파일 명이면 에러 발생
                else {
                	throw new IllegalStateException("허용된 형식이 아닙니다!!");
                }
                // 각 이름은 겹치면 안되므로 나노 초까지 동원하여 지정
                String fileName = productAddRequestDto.getName() + originalFileExtension;

                // 저장된 파일로 변경하여 이를 보여주기 위함
                url = filePath + "/" + fileName;
                File file = new File(url);
                try {
                	productAddRequestDto.getImgFile().transferTo(file);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
		}
		
		Product product = Product.builder()
								.name(productAddRequestDto.getName())
								.category(productAddRequestDto.getCategory())
								.price(productAddRequestDto.getPrice())
								.img(url)
								.supplierId(supplierId)
								.view(0)
								.rate(0.0)
								.salesVolume(0)
								.date(LocalDate.now().toString())
								.build();
								
		return productRepository.save(product).get_id();
	}

	public List<Product> getProducts(String name) {
		return productRepository.findByName(name);
	}

	public List<Product> getMyProducts(String supplierId) {
		return productRepository.findBySupplierId(supplierId);
	}

	public void deleteProduct(String supplierId, String productId) {
		Optional<Product> productWrapper = productRepository.findById(productId);
		
		if(!productWrapper.isPresent())
			throw new NoSuchElementException("존재하지 않는 상품입니다!!");
		if(!productWrapper.get().getSupplierId().equals(supplierId))
			throw new NoSuchElementException("잘못된 접근입니다!!");
		
		productRepository.deleteById(productId);
	}
}
