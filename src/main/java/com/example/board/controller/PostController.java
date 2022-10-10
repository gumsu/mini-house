package com.example.board.controller;

import com.example.board.request.PostSearchRequest;
import com.example.board.request.PostUpdateRequest;
import com.example.board.service.PostService;
import com.example.board.request.PostCreateRequest;
import com.example.board.response.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class PostController {
    private final PostService postService;

    // 등록
    @PostMapping("/post")
    public Long create(@RequestBody @Valid PostCreateRequest postCreateRequest) {
        postCreateRequest.validate();
        return postService.create(postCreateRequest);
    }

    // 전체 조회
    @GetMapping()
    public List<PostResponse> getAll(@ModelAttribute PostSearchRequest postSearchRequest) {
        return postService.getAll(postSearchRequest);
    }

    // 1개 조회
    @GetMapping("/{id}")
    public Optional<PostResponse> findById(@PathVariable Long id) {
        return postService.findOne(id);
    }

    // 수정
    @PatchMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody @Valid PostUpdateRequest postUpdateRequest) {
        return postService.update(id, postUpdateRequest);
    }

    // 삭제
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        postService.delete(id);
    }
}
