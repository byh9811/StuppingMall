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
import com.nerds.stuppingmall.dto.MemberDto;
import com.nerds.stuppingmall.dto.ProductDetailDto;
import com.nerds.stuppingmall.dto.ProductDto;
import com.nerds.stuppingmall.repository.MemberRepository;
import com.nerds.stuppingmall.repository.ProductRepository;
import java.time.LocalDate;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	
	public ProductDetailDto getProduct(String id) {
		Optional<Product> productWrapper = productRepository.findById(id);

		if(!productWrapper.isPresent())
			throw new NoSuchElementException("해당 상품이 존재하지 않습니다!!");
		
		Product product = productWrapper.get();
		product.set_id(id);
		return new ProductDetailDto(product);
	}

	public String addProduct(String companyName, ProductDto productDto) {
		String basePath = "C:\\img";
		String filePath = basePath + "/" + companyName;
		String url = null;
		File folder = new File(filePath);
		
		if(!folder.exists())
			folder.mkdirs();
		
		if(!productDto.getImgFile().isEmpty()) {
            // jpeg, png, gif 파일들만 받아서 처리
            String contentType = productDto.getImgFile().getContentType();
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
                String fileName = productDto.getName() + originalFileExtension;

                // 저장된 파일로 변경하여 이를 보여주기 위함
                url = filePath + "/" + fileName;
                File file = new File(url);
                try {
					productDto.getImgFile().transferTo(file);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
		}
		
		Product product = new Product(
				null, productDto.getName(), productDto.getCategory(),
				productDto.getPrice(), url, companyName,
				0, 0, 0, LocalDate.now().toString());
		
		return productRepository.save(product).get_id();
	}

	public List<ProductDetailDto> getProducts(String name) {
		List<Product> products = productRepository.findByName(name);
		List<ProductDetailDto> productsDto = new ArrayList<>();
		
		for(Product product: products)
			productsDto.add(new ProductDetailDto(product));
		
		return productsDto;
	}

	public List<ProductDetailDto> getMyProducts(MemberDto memberDto) {
		List<Product> products = productRepository.findBySeller(memberDto.getName());
		List<ProductDetailDto> productsDto = new ArrayList<>();
		
		for(Product product: products)
			productsDto.add(new ProductDetailDto(product));
		
		return productsDto;
	}

	public void deleteProduct(MemberDto memberDto, String id) {
		Optional<Product> productWrapper = productRepository.findById(id);
		
		if(!productWrapper.isPresent())
			throw new NoSuchElementException("존재하지 않는 상품입니다!!");
		if(!productWrapper.get().getSeller().equals(memberDto.getName()))
			throw new NoSuchElementException("잘못된 접근입니다!!");
		
		productRepository.deleteById(id);
	}
}
