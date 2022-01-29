package com.nerds.stuppingmall.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.domain.Product;
import com.nerds.stuppingmall.domain.Review;

public interface ReviewRepository extends MongoRepository<Review, String> {
	List<Product> findByProductId(String productId);
	List<Product> findByUserId(String userId);
}