package com.example.shop.global.exception;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(){
        super("Category Not Found.");
    }
}
