package com.example.board.service;

import com.example.board.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

//    @Test
//    @DisplayName("게시글을 등록한다.")
//    void register() {
//        // given
//        PostSaveRequest request = PostSaveRequest.builder()
//                .title("제목입니다")
//                .content("내용입니다")
//                .writer("작성자1")
//                .build();
//
//        // when
//        Long id = postService.register(request);
//
//        // then
//        assertThat(id).isEqualTo(1L);
//        assertThat(1L).isEqualTo(postRepository.count());
//    }
//
//    @Test
//    @DisplayName("게시글을 여러 개가 있는 최신 페이지를 조회 한다.")
//    void getList() {
//        // given
//        List<Post> request = IntStream.range(0, 30)
//                .mapToObj(i -> {
//                    return Post.builder()
//                            .title("제목 " + i)
//                            .content("내용 " + i)
//                            .writer("작성자 "+ i)
//                            .build(  );
//                })
//                .collect(Collectors.toList());
//        postRepository.saveAll(request);
//
//        PostSearchRequest postSearchRequest = PostSearchRequest.builder()
//                .page(1)
//                .build();
//
//        // when
//        List<PostResponse> posts = postService.getList(postSearchRequest);
//
//        // then
//        assertThat(posts.size()).isEqualTo(5);
//        assertThat(posts.get(0).getTitle()).isEqualTo("제목 29");
//    }
//
//    @Test
//    @DisplayName("게시글을 한 개 조회한다.")
//    void findOne() {
//        // given
//        List<Post> request = IntStream.range(0, 30)
//                .mapToObj(i -> {
//                    return Post.builder()
//                            .title("제목 " + i)
//                            .content("내용 " + i)
//                            .writer("작성자 "+ i)
//                            .build(  );
//                })
//                .collect(Collectors.toList());
//        postRepository.saveAll(request);
//
//        // when
//        Optional<PostResponse> response = postService.findOne(1L);
//
//        // then
//        assertThat(response.get().getId()).isEqualTo(1L);
//    }
//
//    @Test
//    @DisplayName("게시글 제목을 수정 한다.")
//    void update() {
//        // given
//        PostSaveRequest postSaveRequest = PostSaveRequest.builder()
//                .title("제목입니다")
//                .content("내용입니다")
//                .writer("작성자1")
//                .build();
//
//        Post post = postRepository.save(postSaveRequest.toEntity());
//
//        PostUpdateRequest postUpdateRequest = PostUpdateRequest.builder()
//                .title("바꾼 제목이에요")
//                .content("내용입니다")
//                .writer("작성자1")
//                .build();
//        // when
//        postService.update(post.getId(), postUpdateRequest);
//
//        // then
//        Post updatePost = postRepository.findById(post.getId()).orElseThrow(() -> new RuntimeException("존재하지 않는 글입니다. + id" + post.getId()));
//        assertThat(updatePost.getTitle()).isEqualTo("바꾼 제목이에요");
//        assertThat(updatePost.getContent()).isEqualTo("내용입니다");
//    }
//
//    @Test
//    @DisplayName("게시글을 삭제한다.")
//    void delete() {
//        // given
//        PostSaveRequest postSaveRequest = PostSaveRequest.builder()
//                .title("제목이에요")
//                .content("내용이에요")
//                .writer("작성자2")
//                .build();
//        Post post = postRepository.save(postSaveRequest.toEntity());
//
//        // when
//        postService.delete(post.getId());
//
//        // then
//        assertThat(postRepository.count()).isEqualTo(0);
//    }
//
//    @Test
//    @DisplayName("존재하지 않는 게시글을 조회하면 오류가 발생한다.")
//    void notFoundPost() {
//        // given
//        PostSaveRequest postSaveRequest = PostSaveRequest.builder()
//                .title("제목이에요")
//                .content("내용이에요")
//                .writer("작성자1")
//                .build();
//
//        Post post = postRepository.save(postSaveRequest.toEntity());
//
//        // expected
//        assertThrows(PostNotFound.class, () -> {
//            postService.findOne(post.getId() + 1L);
//        });
//    }
}
