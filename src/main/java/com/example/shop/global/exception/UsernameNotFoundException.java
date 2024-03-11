package com.example.shop.global.exception;

public class UsernameNotFoundException extends RuntimeException{
    public UsernameNotFoundException(){
        super("해당 유저 정보가 없습니다.");
    }
}
