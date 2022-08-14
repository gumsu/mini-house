package com.example.board.domain.post;

import com.example.board.request.PostSaveRequest;
import com.example.board.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    // 게시글 등록
    public Long register(PostSaveRequest request) {
        return postRepository.save(request.toEntity()).getId();
    }

    // 게시글 전체 조회
    public List<PostResponse> listAll() {
        List<Post> postList = postRepository.findAll();

//        List<PostsResponse> responseList = new ArrayList<>();
//        for (Posts posts : postsList) {
//            responseList.add(new PostsResponse(posts));
//        }
//        return responseList;
        return postList.stream().map(PostResponse::new).collect(Collectors.toList());
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
