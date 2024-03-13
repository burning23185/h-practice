package com.example.shop.api.service.impl;

import com.example.shop.api.dto.CartResponseDto;
import com.example.shop.api.dto.ProductResponseDto;
import com.example.shop.api.service.CartService;
import com.example.shop.api.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.shop.api.domain.Constant.COOKIE_CART_KEY;
import static com.example.shop.api.domain.Constant.COOKIE_EXPIRE;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final ProductService productService;
    private final ObjectMapper objectMapper;

    @Override
    public CartResponseDto addProductToCart(Long productId, int quantity, HttpServletRequest request, HttpServletResponse response) {

        CartResponseDto cart = getCart(request);
        ProductResponseDto.Cart cartItem = new ProductResponseDto.Cart(productService.findById(productId));
        cartItem.updateQuantity(quantity);
        cart.getProducts().add(cartItem);

        CartResponseDto newCart = new CartResponseDto(cart.getProducts(),
                calTotalPrice(cart.getProducts()));
        saveCart(response, newCart, COOKIE_EXPIRE);
        return newCart;
    }


    @Override
    public CartResponseDto updateCartItem(HttpServletRequest request, HttpServletResponse response,
                                          Long productId, int quantity) {
        CartResponseDto cart = getCart(request);
        for(ProductResponseDto.Cart item : cart.getProducts()){
            if(Objects.equals(item.getProductId(), productId)){
                item.updateQuantity(quantity);
            }
        }

        CartResponseDto newCart = new CartResponseDto(cart.getProducts(),
                calTotalPrice(cart.getProducts()));
        saveCart(response, newCart, COOKIE_EXPIRE);
        return newCart;
    }

    @Override
    public CartResponseDto removeCartItem(HttpServletRequest request, HttpServletResponse response, Long productId) {
        CartResponseDto cart = getCart(request);
        cart.getProducts().removeIf(item -> Objects.equals(item.getProductId(),productId));

        CartResponseDto newCart = new CartResponseDto(cart.getProducts(),
                calTotalPrice(cart.getProducts()));
        saveCart(response, newCart, COOKIE_EXPIRE);
        return newCart;
    }

    private  boolean saveCart(HttpServletResponse response, CartResponseDto cart, int expiry){
        try {
            String encodeCartJson = URLEncoder.encode(
                    objectMapper.writeValueAsString(cart), StandardCharsets.UTF_8);

            Cookie cookie = new Cookie(COOKIE_CART_KEY, encodeCartJson);
            cookie.setMaxAge(expiry);
            response.addCookie(cookie);
            return true;

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CartResponseDto getCart(HttpServletRequest request) {
        String cartCookieValue = getCartCookieValue(request.getCookies());

        if (cartCookieValue == null)
            return new CartResponseDto(new ArrayList<>(),0);

        try {
            String decodedCookieValue = URLDecoder.decode(cartCookieValue, StandardCharsets.UTF_8);

            return objectMapper.readValue(decodedCookieValue, new TypeReference<CartResponseDto>() {});

        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    private String getCartCookieValue(Cookie[] requestCookies){
        if (requestCookies == null) return null;

        for (Cookie cookie : requestCookies) {
            if (cookie.getName().equals(COOKIE_CART_KEY)) return cookie.getValue();
        }
        return null;
    }
        private int calTotalPrice(List<ProductResponseDto.Cart> products){
        if (products.isEmpty()){
            return 0;
        }
        return products.stream()
                .mapToInt(product -> product.getPrice() * product.getQuantity())
                .sum();
    }

}
