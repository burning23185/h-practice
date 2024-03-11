package com.example.shop.global.exception;

public class DuplicatedEmailException extends RuntimeException{
    public DuplicatedEmailException(){
        super("중복된 이메일 입니다.");
    }
}
