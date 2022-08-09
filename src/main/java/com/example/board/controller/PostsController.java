package com.example.board.controller;

import com.example.board.domain.post.PostsService;
import com.example.board.request.PostsSaveRequest;
import com.example.board.response.PostsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class PostsController {
    private final PostsService postsService;

    // 등록
    @PostMapping("/posts")
    public Long save(@RequestBody PostsSaveRequest postsSaveRequest) {
        return postsService.register(postsSaveRequest);
    }

    // 전체 조회
    @GetMapping("/")
    public List<PostsResponse> listAll() {
        return postsService.listAll();
    }

    // 1개 조회
    @GetMapping("/{id}")
    public Optional<PostsResponse> findById(@PathVariable Long id) {
        return postsService.findOne(id);
    }
}
