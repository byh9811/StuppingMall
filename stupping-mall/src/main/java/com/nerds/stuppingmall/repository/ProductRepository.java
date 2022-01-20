package com.nerds.stuppingmall.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.domain.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
}
