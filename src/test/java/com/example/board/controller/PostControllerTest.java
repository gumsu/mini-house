package com.example.board.controller;

import com.example.board.domain.post.Post;
import com.example.board.repository.PostRepository;
import com.example.board.request.PostSaveRequest;
import com.example.board.request.PostUpdateRequest;
import com.example.board.response.PostResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

//    @Autowired
//    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void clean() {
        postRepository.deleteAll();
    }

//    @Autowired
//    private WebApplicationContext wac;

//    @BeforeEach
//    void setup() {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
//    }

    @Test
    @DisplayName("/post 를 post로 요청 시 게시글을 저장한다.")
    void save() throws Exception {
        // given
        PostSaveRequest request = PostSaveRequest.builder()
                .title("제목입니다.")
                .content("내용입니다")
                .writer("작성자입니다")
                .build();

//        when(postService.register(Mockito.any())).thenReturn(Long.valueOf(1));

        // when
        mockMvc.perform(post("/api/v1/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isOk())
                .andExpect(content().string("1"))
                .andDo(print());
        //then
//        assertEquals(1L, postRepository.count());
    }

    @Test
    @DisplayName("/ 를 get으로 요청 시 게시글을 여러 개 조회한다.")
    void getList() throws Exception {
        // given
        List<Post> request = IntStream.range(0, 30)
                .mapToObj(i -> {
                    return Post.builder()
                            .title("제목 " + i)
                            .content("내용 " + i)
                            .writer("작성자 "+ i)
                            .build(  );
                })
                .collect(Collectors.toList());
        postRepository.saveAll(request);

        // expected
        mockMvc.perform(get("/api/v1?page=1&size=5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(5)))
                .andExpect(jsonPath("$[0].title").value("제목 29"))
                .andDo(print());
    }

    @Test
    @DisplayName("/{id} 를 get으로 요청 시 게시글을 한 개 조회한다.")
    void findById() throws Exception {
        // given
        PostSaveRequest request = PostSaveRequest.builder()
                .title("제목입니다.")
                .content("내용입니다")
                .writer("작성자입니다")
                .build();

        Post post = postRepository.save(request.toEntity());
        PostResponse response = new PostResponse(post);

        // expected
        mockMvc.perform(get("/api/v1/{postId}", response.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        )
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.registerModule(new JavaTimeModule()).writeValueAsString(response)))
                .andDo(print());

    }

    @Test
    @DisplayName("/{id} 를 patch로 요청 시 게시글을 수정한다.")
    void update() throws Exception {
        // given
        PostSaveRequest data = PostSaveRequest.builder()
                .title("제목입니다.")
                .content("내용입니다")
                .writer("작성자입니다")
                .build();

        Post post = postRepository.save(data.toEntity());

        // when
        PostUpdateRequest request = PostUpdateRequest.builder()
                .title("변경한 제목")
                .content("변경한 내용")
                .writer("작성자") // 작성자입니다
                .build();

        // then
        mockMvc.perform(patch("/api/v1/{id}", post.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isOk())
                .andExpect(content().string("1"))
                .andDo(print());

//        Assertions.assertThat(postRepository.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("게시글 등록 시 제목은 필수다.")
    void verify() throws Exception {
        // given
        PostSaveRequest request = PostSaveRequest.builder()
                .title("")
                .content("내용입니다")
                .writer("작성자입니다")
                .build();

        // expected
        mockMvc.perform(post("/api/v1/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(objectMapper.writeValueAsString(request))
                )
//                .andExpect(jsonPath("$.title").value("제목을 입력해주세요."))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(jsonPath("$.validation.title").value("제목을 입력해주세요."))
                .andDo(print());
    }

    @Test
    @DisplayName("/ 를 get으로 요청할 때 페이지를 0으로 하면 첫 페이지를 가져온다.")
    void zeroPage() throws Exception {
        // given
        List<Post> request = IntStream.range(0, 30)
                .mapToObj(i -> {
                    return Post.builder()
                            .title("제목 " + i)
                            .content("내용 " + i)
                            .writer("작성자 "+ i)
                            .build(  );
                })
                .collect(Collectors.toList());
        postRepository.saveAll(request);

        // expected
        mockMvc.perform(get("/api/v1?page=0&size=5")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(5)))
                .andExpect(jsonPath("$[0].title").value("제목 29"))
                .andDo(print());
    }

    @Test
    @DisplayName("/{id}를 delete로 요청 시 게시글을 삭제한다.")
    void delete() throws Exception {
        // given
        PostSaveRequest postSaveRequest = PostSaveRequest.builder()
                .title("게시글제목")
                .content("내용임")
                .writer("작성자1")
                .build();

        Post post = postRepository.save(postSaveRequest.toEntity());

        // expected
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/{id}", post.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("존재하지 않는 게시글을 조회하면 오류가 발생한다.")
    void notFoundPost() throws Exception {
        mockMvc.perform(get("/api/v1/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                )
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    @DisplayName("게시글 등록 시 바보는 포함될 수 없다.")
    void invalidRequest() throws Exception {
        PostSaveRequest postSaveRequest = PostSaveRequest.builder()
                .title("바보")
                .content("내용")
                .writer("작성자")
                .build();

        mockMvc.perform(post("/api/v1/post")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(objectMapper.writeValueAsString(postSaveRequest))
                )
                .andExpect(status().isBadRequest())
                .andDo(print());
    }
}