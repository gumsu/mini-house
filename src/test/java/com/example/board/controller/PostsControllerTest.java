package com.example.board.controller;

import com.example.board.domain.post.PostsRepository;
import com.example.board.domain.post.PostsService;
import com.example.board.request.PostsSaveRequest;
import com.example.board.response.PostsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest
@SpringBootTest
@AutoConfigureMockMvc
class PostsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostsService postsService;

    @MockBean
    private PostsRepository postsRepository;

    @Autowired
    private WebApplicationContext wac;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    @DisplayName("/posts 를 post로 요청 시 게시글을 저장한다.")
    void save() throws Exception {
        // given
        PostsSaveRequest request = new PostsSaveRequest("제목입니다.", "내용입니다", "작성자입니다");

        when(postsService.register(Mockito.any())).thenReturn(Long.valueOf(1));

        // expected
        mockMvc.perform(post("/api/v1/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
//                    .content("{\"title\": \"제목입니다.\", \"content\": \"내용입니다.\", \"writer\": \"작성자입니다\"}")
                        .content(new ObjectMapper().writeValueAsString(request))
                )
                .andExpect(status().isOk())
                .andExpect(content().string("1"))
                .andDo(print());
    }

    @Test
    @DisplayName("/ 를 get으로 요청 시 게시글을 전체 조회한다.")
    void listAll() throws Exception {
        PostsSaveRequest request = new PostsSaveRequest("제목입니다.", "내용입니다", "작성자입니다");
        postsRepository.save(request.toEntity());

        mockMvc.perform(get("/api/v1/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("/{id} 를 get으로 요청 시 게시글을 한 개 조회한다.")
    void findById() throws Exception {
        // given
        PostsSaveRequest request = new PostsSaveRequest("제목입니다.", "내용입니다", "작성자입니다");
        PostsResponse response = new PostsResponse(request.toEntity());

        when(postsService.findOne(1L)).thenReturn(java.util.Optional.of(response));

        mockMvc.perform(get("/api/v1/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        )
                .andExpect(status().isOk())
//                .andExpect(content().string(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(response)))
                .andDo(print());
    }

    @Test
    @DisplayName("/{id} 를 patch로 요청 시 게시글을 수정한다.")
    void update() throws Exception {
        // given
        PostsSaveRequest request = new PostsSaveRequest("제목입니다.", "내용입니다", "작성자입니다");
        postsRepository.save(request.toEntity());

        // when
        Map<String, String> input = new HashMap<>();
        input.put("title","변경한제목");
        input.put("content", "변경한내용");
        input.put("writer", "작성자입니다.");

        // then
        mockMvc.perform(patch("/api/v1/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(new ObjectMapper().writeValueAsString(input))
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("게시글 등록 시 제목은 필수다.")
    void verify() throws Exception {
        PostsSaveRequest request = new PostsSaveRequest("", "내용입니다.", "작성자입니다");

        mockMvc.perform(post("/api/v1/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(new ObjectMapper().writeValueAsString(request))
                )
//                .andExpect(jsonPath("$.title").value("제목을 입력해주세요."))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(jsonPath("$.validation.title").value("제목을 입력해주세요."))
                .andDo(print());
    }
}