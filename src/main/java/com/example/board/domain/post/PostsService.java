package com.example.board.domain.post;

import com.example.board.request.PostsSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    // 게시글 등록
    public Long register(PostsSaveRequest request) {
        return postsRepository.save(request.toEntity()).getId();
    }
}
