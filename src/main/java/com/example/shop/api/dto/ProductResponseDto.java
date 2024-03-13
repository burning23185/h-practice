package com.example.shop.api.dto;

import com.example.shop.api.domain.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class ProductResponseDto {
    @Getter
    public static class Get {

        private final Long productId;

        private final String productName;

        private final int price;

        private final int quantity;

        private final String introduction;

        private final CategoryResponseDto category;

        public Get(Product product) {
            this.productId = product.getId();
            this.productName = product.getProductName();
            this.price = product.getPrice();
            this.quantity = product.getQuantity();
            this.introduction = product.getIntroduction();
            this.category = new CategoryResponseDto(product.getCategory());
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Cart {

        private Long productId;

        private String productName;

        private int price;

        private int quantity;

        private String introduction;

        private CategoryResponseDto category;

        public Cart(Product product) {
            this.productId = product.getId();
            this.productName = product.getProductName();
            this.price = product.getPrice();
            this.quantity = product.getQuantity();
            this.introduction = product.getIntroduction();
            this.category = new CategoryResponseDto(product.getCategory());
        }

        public void updateQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}