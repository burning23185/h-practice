package com.example.shop.api.dto;

import com.example.shop.api.domain.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryResponseDto {
    private Long categoryId;
    private String categoryName;

    public CategoryResponseDto(Category category) {
        this.categoryId = category.getId();
        this.categoryName = makeCategoryNames(category);
    }

    private String makeCategoryNames(Category category){
        StringBuilder sb = new StringBuilder();

        while(category.getId() != 1L){
            sb.insert(0, category.getCategoryName());
            sb.insert(0," > ");
            category = category.getParent();
        }

        return sb.toString();
    }
}
