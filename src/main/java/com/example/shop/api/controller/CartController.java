package com.example.shop.api.controller;

import com.example.shop.api.dto.CartResponseDto;
import com.example.shop.api.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    @PostMapping
    public ResponseEntity<CartResponseDto> addCart(@RequestParam Long productId, @RequestParam int quantity, HttpServletRequest request, HttpServletResponse response){
        return ResponseEntity.ok(cartService.addProductToCart(productId,quantity,request,response));
    }

    @GetMapping
    public ResponseEntity<CartResponseDto> getCart(HttpServletRequest request){
        return ResponseEntity.ok(cartService.getCart(request));
    }
    @PutMapping
    public ResponseEntity<CartResponseDto> updateCart(@RequestParam Long productId, @RequestParam int quantity, HttpServletRequest request, HttpServletResponse response){
        return ResponseEntity.ok(cartService.updateCartItem(request, response, productId, quantity));
    }
    @DeleteMapping
    public ResponseEntity<CartResponseDto> removeCartItem(@RequestParam Long productId, HttpServletRequest request, HttpServletResponse response){
        return ResponseEntity.ok(cartService.removeCartItem(request, response, productId));
    }

}
