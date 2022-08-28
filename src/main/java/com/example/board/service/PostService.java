package com.example.board.service;

import com.example.board.domain.post.Post;
import com.example.board.repository.PostRepository;
import com.example.board.request.PostSaveRequest;
import com.example.board.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    // 게시글 등록
    public Long register(PostSaveRequest request) {
        return postRepository.save(request.toEntity()).getId();
    }

    // 게시글 여러 개 조회
    public List<PostResponse> getList(Pageable page) {
        return postRepository.findAll(page).stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }

    // 게시글 한 개 조회
    public Optional<PostResponse> findOne(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("게시물이 존재하지 않습니다."));
        return Optional.of(new PostResponse(post));
    }

    // 게시글 수정
    public Long update(Long id, PostSaveRequest request) {
        Optional<Post> post = postRepository.findById(id);
        post.get().update(
                request.getTitle(),
                request.getContent(),
                request.getWriter());
        return request.toEntity().getId();
    }
}
