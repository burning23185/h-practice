package com.example.shop.api.repository.querydsl;

import com.example.shop.api.domain.Product;
import com.example.shop.api.dto.ProductResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryCustom {
    Page<Product> getProductPage(Pageable pageable);
}
