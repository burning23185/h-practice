package com.example.shop.global.exception.error;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CustomErrorResponse {
    String statusCode;
    String requestUrl;
    String message;
    String resultCode;

    List<CustomError> errorList;
}