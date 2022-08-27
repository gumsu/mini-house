package com.example.board.domain.post;

import com.example.board.request.PostSaveRequest;
import com.example.board.response.PostResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void setup() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("게시글을 등록한다.")
    void register() {
        // given
        PostSaveRequest request = PostSaveRequest.builder()
                .title("제목입니다")
                .content("내용입니다")
                .writer("작성자1")
                .build();

        // when
        Long id = postService.register(request);

        // then
        assertThat(id).isEqualTo(1L);
        assertThat(1L).isEqualTo(postRepository.count());
    }

    @Test
    @DisplayName("게시글을 전체 조회 한다.")
    void listAll() {
        // given

        // when

        // then
    }

    @Test
    @DisplayName("게시글을 한 개 조회한다.")
    void findOne() {
        // given
        PostSaveRequest request1 = PostSaveRequest.builder()
                .title("제목입니다")
                .content("내용입니다")
                .writer("작성자1")
                .build();
        Post post = postRepository.save(request1.toEntity());

        // when
        Optional<PostResponse> response = postService.findOne(post.getId());

        // then
        assertThat(response.get().getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("게시글을 수정 한다.")
    void update() {
        // given

        // when

        // then

    }
}
