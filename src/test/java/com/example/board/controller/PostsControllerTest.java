package com.example.board.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class PostsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostsController postsController;

    @Test
    @DisplayName("/posts 를 post로 요청 시 게시글을 저장한다.")
    void save() throws Exception {
        mockMvc.perform(post("/api/v1/posts")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"title\": \"제목입니다.\", \"content\": \"내용입니다.\", \"writer\": \"작성자입니다\"}")
                )
                .andExpect(status().isOk())
                .andDo(print());
    }
}