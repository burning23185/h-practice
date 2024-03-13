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

    // ADMIN_TOKEN
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    @Override
    public boolean signup(UserRequestDto.Signup requestDto) {

        // 유저네임 중복 체크
        if (userRepository.existsByUsername(requestDto.getEmail()))
            throw new DuplicatedEmailException();

        // 관리자 여부 체크
        if (!requestDto.isAdmin()) {
            userRepository.save(new User(requestDto,
                    passwordEncoder.encode(requestDto.getPassword()),
                    UserRoleEnum.USER));
            return true;
        }

        // 관리자 패스워드 체크
        if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
            throw new CreateAdminFailException();
        }

        // MANAGER 권한 체크
        userRepository.save(new User(requestDto,
                passwordEncoder.encode(requestDto.getPassword()),
                UserRoleEnum.ADMIN));
        return true;
    }

    @Override
    public boolean login(UserRequestDto.Login requestDto, HttpServletResponse res) {
        String username = requestDto.getEmail();
        String password = requestDto.getPassword();

        // 사용자 확인
        User user = userRepository.findByUsername(username).orElseThrow(EmailNotFoundException::new);

        // 비밀번호 확인
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new LoginFailException();

        // JWT 생성 및 쿠키에 저장 후 Response 객체에 추가
        jwtUtil.addJwtToCookie(jwtUtil.createToken(user.getUsername(), user.getRole()), res);
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