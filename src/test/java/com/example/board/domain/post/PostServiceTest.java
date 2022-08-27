package com.example.board.domain.post;

import com.example.board.request.PostSaveRequest;
import com.example.board.response.PostResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    @DisplayName("게시글을 여러 개가 있는 최신 페이지를 조회 한다.")
    void getList() {
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

        // when
        List<PostResponse> posts = postService.getList(0);

        // then
        assertThat(posts.size()).isEqualTo(5);
        assertThat(posts.get(0).getTitle()).isEqualTo("제목 29");
    }

    @Test
    @DisplayName("게시글을 한 개 조회한다.")
    void findOne() {
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

        // when
        Optional<PostResponse> response = postService.findOne(1L);

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
