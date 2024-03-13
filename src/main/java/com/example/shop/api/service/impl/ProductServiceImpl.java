package com.example.shop.api.service.impl;

import com.example.shop.api.domain.Product;
import com.example.shop.api.dto.ProductRequestDto;
import com.example.shop.api.dto.ProductResponseDto;
import com.example.shop.api.repository.ProductRepository;
import com.example.shop.api.service.CategoryService;
import com.example.shop.api.service.ProductService;
import com.example.shop.global.exception.ProductNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Override
    @Transactional
    public ProductResponseDto.Get createProduct(ProductRequestDto requestDto){
        return new ProductResponseDto.Get(productRepository.save(new Product(requestDto,
                categoryService.findById(requestDto.getCategoryId()))));
    }

    @Override
    public ProductResponseDto.Get getProduct(Long productId){
        return new ProductResponseDto.Get(findById(productId));
    }

    @Override
    public Product findById(Long productId){
        return productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
    }

//    public Page<ProductResponseDto> getProductPage(int page, int size, String sortBy, boolean isAsc) {
//        Sort sort = Sort.by(Sort.Direction.DESC, "createAt");
//
//        if(sortBy.equals("productName") || sortBy.equals("price"))
//            sort = Sort.by(isAsc ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
//
//        return productRepository.getProductPage(PageRequest.of(page, size, sort)).map(ProductResponseDto::new);
//    }
    // 아래 쿼리보다 느림 100만건 기준 약 7분 30초  // 아래코드 약 1분 20초
    @Override
    public Page<ProductResponseDto.Get> getProductPage(int page, int size, String sortBy, boolean isAsc) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createAt");

        if(sortBy.equals("productName") || sortBy.equals("price"))
            sort = Sort.by(isAsc ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);

        return productRepository.findAll(PageRequest.of(page, size, sort)).map(ProductResponseDto.Get::new);
    }
}
