package com.example.shop.api.controller;

import com.example.shop.api.domain.enums.UserRoleEnum;
import com.example.shop.api.dto.ProductRequestDto;
import com.example.shop.api.dto.ProductResponseDto;
import com.example.shop.api.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @Secured(UserRoleEnum.Authority.ADMIN)
    @PostMapping
    public ResponseEntity<ProductResponseDto.Get> createProduct(@Valid @RequestBody ProductRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(requestDto));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDto.Get> getProduct(@PathVariable Long productId){
        return ResponseEntity.ok(productService.getProduct(productId));
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponseDto.Get>> getProductList(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy,
            @RequestParam boolean isAsc
            ){
        return ResponseEntity.ok(productService.getProductPage(page-1, size, sortBy, isAsc));
    }
}
