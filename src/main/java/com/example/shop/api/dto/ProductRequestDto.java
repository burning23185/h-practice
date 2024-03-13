package com.example.shop.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductRequestDto {
    @NotBlank
    @Size(min = 1, max = 50)
    private String productName;

    private int price;
    private int quantity;

    @Size(max = 500)
    private String introduction = "None";

    private Long categoryId = 1L;

}
