package com.example.board.service;

import com.example.board.domain.auth.JWTTokenProvider;
import com.example.board.domain.post.Post;
import com.example.board.domain.post.PostEditor;
import com.example.board.domain.user.User;
import com.example.board.exception.PostNotFound;
import com.example.board.repository.PostRepository;
import com.example.board.request.PostCreateRequest;
import com.example.board.request.PostSearchRequest;
import com.example.board.request.PostUpdateRequest;
import com.example.board.response.PostResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private final JWTTokenProvider jwtTokenProvider;

    // 게시글 등록
    public Long create(PostCreateRequest request, String accessToken) {
        jwtTokenProvider.validateToken(accessToken);
        String userEmail = jwtTokenProvider.getUserPk(accessToken);
        User user = userService.findOneByEmail(userEmail);
        return postRepository.save(request.toEntity(user)).getId();
    }

    // 게시글 여러 개 조회
    public List<PostResponse> getAll(PostSearchRequest request) {
        return postRepository.getList(request).stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }

    // 게시글 한 개 조회
    public Optional<PostResponse> findOne(Long id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFound::new);
        return Optional.of(new PostResponse(post));
    }

    // 게시글 수정
    public Long update(Long id, PostUpdateRequest request, String accessToken) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFound::new);

        jwtTokenProvider.validateToken(accessToken);

        PostEditor.PostEditorBuilder editorBuilder = post.toEditor(); // 기존 데이터
        PostEditor postEditor = editorBuilder // 새로운 데이터
                .title(request.getTitle())
                .content(request.getContent())
                .build();
        post.toEdit(postEditor);
        return post.getId();
    }

    public void delete(Long id, String accessToken) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFound::new);
        jwtTokenProvider.validateToken(accessToken);

        postRepository.delete(post);
    }
}
