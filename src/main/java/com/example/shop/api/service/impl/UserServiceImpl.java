package com.example.shop.api.service.impl;

import com.example.shop.api.domain.User;
import com.example.shop.api.domain.enums.UserRoleEnum;
import com.example.shop.api.dto.UserRequestDto;
import com.example.shop.api.repository.UserRepository;
import com.example.shop.api.service.UserService;
import com.example.shop.global.exception.*;
import com.example.shop.global.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public boolean login(UserRequestDto.Login requestDto, HttpServletResponse res) {

        User user = findUserByUsername(requestDto.getEmail());

        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword()))
            throw new LoginFailException();

        jwtUtil.addJwtToCookie(jwtUtil.createToken(user.getUsername(), user.getRole()), res);
        return true;
    }

    @Override
    public boolean signup(UserRequestDto.Signup requestDto) {

        if (userRepository.existsByUsername(requestDto.getEmail()))
            throw new DuplicatedEmailException();

        return requestDto.isAdmin() ? saveAsAdmin(requestDto) : saveAsUer(requestDto);
    }

    private boolean saveAsUer(UserRequestDto.Signup requestDto){
        userRepository.save(new User(requestDto,
                passwordEncoder.encode(requestDto.getPassword()),
                UserRoleEnum.USER));
        return true;
    }

    private boolean saveAsAdmin(UserRequestDto.Signup requestDto){

        String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

        if (!ADMIN_TOKEN.equals(requestDto.getAdminToken()))
            throw new CreateAdminFailException();

        userRepository.save(new User(requestDto,
                passwordEncoder.encode(requestDto.getPassword()),
                UserRoleEnum.ADMIN));
        return true;
    }

    @Override
    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(UsernameNotFoundException::new);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(UsernameNotFoundException::new);
    }
}