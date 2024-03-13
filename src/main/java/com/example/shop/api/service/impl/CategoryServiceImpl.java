package com.example.shop.api.service.impl;

import com.example.shop.api.domain.Category;
import com.example.shop.api.dto.CategoryRequestDto;
import com.example.shop.api.dto.CategoryResponseDto;
import com.example.shop.api.repository.CategoryRepository;
import com.example.shop.api.service.CategoryService;
import com.example.shop.global.exception.CategoryNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public Category findById(Long categoryId){
        System.out.println("categoryId = " + categoryId);
        return categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
    }
    @Transactional
    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto requestDto) {
        Category category= new Category(requestDto.getCategoryName(),
                findById(requestDto.getParentId()));
        categoryRepository.save(category);
        return new CategoryResponseDto(category);
    }

    @Override
    @Transactional
    public CategoryResponseDto updateCategory(CategoryRequestDto requestDto) {
        Category category = findById(requestDto.getCategoryId())
                .updateCategory(requestDto.getCategoryName(),
                        findById(requestDto.getParentId()));

        return new CategoryResponseDto(category);
    }

    @Override
    public CategoryResponseDto getCategory(Long categoryId) {
        return new CategoryResponseDto(findById(categoryId));
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.delete(findById(categoryId));
    }
}
