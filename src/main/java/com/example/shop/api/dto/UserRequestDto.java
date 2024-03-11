package com.example.shop.api.dto;

import com.example.shop.api.domain.GenderEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class UserRequestDto {
    @Getter
    @AllArgsConstructor
    @RequiredArgsConstructor
    public static class Signup {
        @NotBlank
        @Pattern(regexp = "^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\\.[A-Za-z0-9\\-]+$", message = "이메일 형식에 맞지 않습니다.")
        private String email;

        @NotBlank
        @Pattern(regexp="^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,15}$", message="비밀번호가 적절하지 않습니다.")
        private String password;

        @NotNull
        private GenderEnum gender;

        @NotBlank
        @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "올바른 연락처를 입력해주세요.")
        private String contactNumber;

        @NotBlank
        @Size(max = 1500)
        private String address;

        private boolean admin = false;
        private String adminToken = "";
    }
    @Getter
    public static class Login {
        @NotBlank
        @Pattern(regexp = "^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\\.[A-Za-z0-9\\-]+$", message = "이메일 형식에 맞지 않습니다.")
        private String email;

        @NotBlank
        private String password;
    }
}