package com.example.board.service;

import com.example.board.repository.UserRepository;
import com.example.board.request.SignUpRequest;
import com.example.board.request.SignInRequest;
import com.example.board.response.SignInResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("회원가입을 할 수 있다.")
    void register() {
        // given
        SignUpRequest request = SignUpRequest.builder()
                .name("이름")
                .email("abc@naver.com")
                .password("1234")
                .build();

        // when
        Long id = userService.signUp(request);

        // then
        assertThat(id).isEqualTo(1L);
    }

    @Test
    @DisplayName("로그인을 할 수 있다.")
    void signIn() {
        // given
        SignUpRequest signUp = SignUpRequest.builder()
            .name("이름")
            .email("abc@naver.com")
            .password("1234")
            .build();
        userService.signUp(signUp);

        SignInRequest request = SignInRequest.builder()
            .email(signUp.getEmail())
            .password(signUp.getPassword())
            .build();

        // when
        SignInResponse response = userService.signIn(request);

        // then
        assertThat(response.getMemberName()).isEqualTo(signUp.getName());
    }
}