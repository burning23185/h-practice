package com.example.shop.api.service;

import com.example.shop.api.domain.Category;
import com.example.shop.api.dto.CategoryRequestDto;
import com.example.shop.api.dto.CategoryResponseDto;

public interface CategoryService {
    Category findById(Long categoryId);
    CategoryResponseDto createCategory(CategoryRequestDto requestDto);

    CategoryResponseDto updateCategory(CategoryRequestDto requestDto);

    CategoryResponseDto getCategory(Long categoryId);

    void deleteCategory(Long categoryId);
}
