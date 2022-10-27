package com.example.board.request;

import com.example.board.domain.post.Post;
import com.example.board.exception.InvalidRequest;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class PostCreateRequest {

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;
    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    @Builder
    public PostCreateRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Post toEntity() {
        return Post.builder()
            .title(title)
            .content(content)
            .createdAt(LocalDateTime.now())
            .modifiedAt(null)
            .views(0L)
            .likes(0)
            .build();
    }

    public void validate() {
        if (title.contains("바보")) {
            throw new InvalidRequest("title", "제목에 바보를 포함할 수 없습니다.");
        }
    }
}
