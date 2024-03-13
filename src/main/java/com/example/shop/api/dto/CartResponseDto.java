package com.example.shop.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartResponseDto {
    private  List<ProductResponseDto.Cart> products;
    private  int totalPrice;
}
