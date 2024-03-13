package com.example.shop.api.domain;

import com.example.shop.api.domain.enums.GenderEnum;
import com.example.shop.api.domain.enums.UserRoleEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class UserHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long user_id;

    @Column(nullable = false)
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

    public UserHistory(User user){
        this.user_id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.gender = user.getGender();
        this.contactNumber = user.getContactNumber();
        this.address = user.getAddress();
        this.role = user.getRole();
    }
}
