package com.example.shop.api.service;

import com.example.shop.api.dto.CartResponseDto;
import com.example.shop.api.dto.ProductResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface CartService {
    CartResponseDto addProductToCart(Long productId, int quantity, HttpServletRequest request, HttpServletResponse response);

    CartResponseDto getCart(HttpServletRequest request);

    CartResponseDto updateCartItem(HttpServletRequest request, HttpServletResponse response,
                                   Long productId, int quantity);

    CartResponseDto removeCartItem(HttpServletRequest request, HttpServletResponse response, Long productId);
}
