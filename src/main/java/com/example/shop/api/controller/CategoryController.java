package com.example.shop.api.controller;

import com.example.shop.api.domain.enums.UserRoleEnum;
import com.example.shop.api.dto.CategoryRequestDto;
import com.example.shop.api.dto.CategoryResponseDto;
import com.example.shop.api.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@Secured(UserRoleEnum.Authority.ADMIN)
@RestController
@RequestMapping("api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(@Valid @RequestBody CategoryRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.CREATED).
                body(categoryService.createCategory(requestDto));
    }
    @PutMapping
    public ResponseEntity<CategoryResponseDto> updateCategory(@Valid @RequestBody CategoryRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.CREATED).
                body(categoryService.updateCategory(requestDto));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDto> getProduct(@PathVariable Long categoryId){
        return ResponseEntity.ok(categoryService.getCategory(categoryId));
    }
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable Long categoryId){
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok(true);
    }
}
