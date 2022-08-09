package com.example.board.controller;

import com.example.board.domain.post.PostsService;
import com.example.board.request.PostsSaveRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
