package com.example.board.service;

import com.example.board.domain.member.Member;
import com.example.board.repository.MemberRepository;
import com.example.board.request.MemberSaveRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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
        Assertions.assertThat(id).isEqualTo(1L);
    }
}