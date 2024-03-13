package com.example.shop.api.service;

import com.example.shop.api.domain.Product;
import com.example.shop.api.dto.ProductRequestDto;
import com.example.shop.api.dto.ProductResponseDto;
import org.springframework.data.domain.Page;

public interface ProductService {
    ProductResponseDto.Get createProduct(ProductRequestDto requestDto);

    ProductResponseDto.Get getProduct(Long productId);

    Page<ProductResponseDto.Get> getProductPage(int page, int size, String sortBy, boolean isAsc);

    Product findById(Long productId);


}
