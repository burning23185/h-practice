package com.example.shop.global.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException() {
        super("Product Not Found");
    }
}
