package com.example.shop.api.domain;

import com.example.shop.api.domain.enums.GenderEnum;
import com.example.shop.api.domain.enums.UserRoleEnum;
import com.example.shop.api.dto.UserRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private GenderEnum gender;

    @Column(nullable = false)
    private String contactNumber;

    @Column(nullable = false)
    @Size(max = 1500)
    private String address;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(UserRequestDto.Signup requestDto, String password, UserRoleEnum role){
        this.username = requestDto.getEmail();
        this.password = password;
        this.gender = requestDto.getGender();
        this.contactNumber = requestDto.getContactNumber();
        this.address = requestDto.getAddress();
        this.role = role;
    }
}