package com.example.shop.api.service;

import com.example.shop.api.domain.User;
import com.example.shop.api.dto.UserRequestDto;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {

    boolean signup(UserRequestDto.Signup requestDto);
    boolean login(UserRequestDto.Login requestDto, HttpServletResponse res);
    User findUserById(Long userId);
    User findUserByUsername(String username);
}
