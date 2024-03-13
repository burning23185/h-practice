package com.example.shop.api.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class CategoryRequestDto {
    private Long categoryId;

    @Size(min = 1, max = 30)
    private String categoryName;

    private Long parentId = 1L;
}
