package com.example.shop.global.exception.error;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CustomError {
    private String field;
    private String messege;
    private String invalidValue;
}
