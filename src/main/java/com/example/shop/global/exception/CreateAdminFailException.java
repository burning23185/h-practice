package com.example.shop.global.exception;

public class CreateAdminFailException extends RuntimeException {
    public CreateAdminFailException(){
        super("관리자 등록에 실패했습니다.");
    }
}
