package com.example.board.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.board.request.SignInRequest;
import com.example.board.request.SignUpRequest;
import com.example.board.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("/signup 를 요청 시 회원가입 할 수 있다.")
    void signup() throws Exception {
        // given
        SignUpRequest request = SignUpRequest.builder()
            .name("이름")
            .email("abcd@naver.com")
            .password("1234")
            .build();

        // expected
        mockMvc.perform(post("/api/v1/user/sign-up")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @DisplayName("/signin 을 요청 시 로그인을 할 수 있다.")
    void signin() throws Exception {
        // given
        SignUpRequest signUpRequest = SignUpRequest.builder()
            .name("이름")
            .email("abcd@naver.com")
            .password("1234")
            .build();

        userService.signUp(signUpRequest);
        SignInRequest request = SignInRequest.builder()
            .email("abcd@naver.com")
            .password("1234")
            .build();

        mockMvc.perform(post("/api/v1/user/sign-in")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andDo(print());
    }
}
