package com.example.board.service;

import com.example.board.repository.MemberRepository;
import com.example.board.request.MemberSaveRequest;
import com.example.board.request.SignInRequest;
import com.example.board.response.SignInResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원가입을 할 수 있다.")
    void register() {
        // given
        MemberSaveRequest request = MemberSaveRequest.builder()
                .name("이름")
                .email("abc@naver.com")
                .password("1234")
                .build();

        // when
        Long id = memberService.register(request);

        // then
        assertThat(id).isEqualTo(1L);
    }

    @Test
    @DisplayName("로그인을 할 수 있다.")
    void signIn() {
        // given
        MemberSaveRequest signUp = MemberSaveRequest.builder()
            .name("이름")
            .email("abc@naver.com")
            .password("1234")
            .build();
        memberService.register(signUp);

        SignInRequest request = SignInRequest.builder()
            .email(signUp.getEmail())
            .password(signUp.getPassword())
            .build();

        // when
        SignInResponse response = memberService.signIn(request);

        // then
        assertThat(response.getMemberName()).isEqualTo(signUp.getName());
    }
}