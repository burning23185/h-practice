package com.example.shop.api.controller;


import com.example.shop.api.dto.UserRequestDto;
import com.example.shop.api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Boolean> loginPage(@Valid @RequestBody UserRequestDto.Login requestDto) {
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
    @PostMapping("/signup")
    public ResponseEntity<Boolean> signupPage(@Valid @RequestBody UserRequestDto.Signup requestDto) {
        if(userService.signup(requestDto))
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        return new ResponseEntity<>( false, HttpStatus.CONFLICT);
    }
}