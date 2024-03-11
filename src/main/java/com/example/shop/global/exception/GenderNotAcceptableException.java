package com.example.shop.global.exception;

public class GenderNotAcceptableException extends RuntimeException{
    public GenderNotAcceptableException(){
        super("NOT Acceptable Gender");
    }
}
