package com.example.board.service;

import com.example.board.domain.auth.JWTTokenProvider;
import com.example.board.domain.user.User;
import com.example.board.repository.UserRepository;
import com.example.board.request.SignUpRequest;
import com.example.board.request.SignInRequest;
import com.example.board.response.SignInResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final JWTTokenProvider jwtTokenProvider;

    public Long signUp(SignUpRequest request) {
        return userRepository.save(request.toEntity()).getId();
    }

    public SignInResponse signIn(SignInRequest request) {
        User user = userRepository.findByEmail(request.getEmail().toLowerCase())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 email 입니다."));
        String token = jwtTokenProvider.generateToken(user.getEmail(), user.getRole());
        return new SignInResponse(user, token);
    }
}
