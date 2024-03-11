package com.example.shop.global.exception;

public class EmailNotFoundException extends RuntimeException{
    public EmailNotFoundException(){
        super("해당 이메일을 찾을 수 없습니다.");
    }
}
