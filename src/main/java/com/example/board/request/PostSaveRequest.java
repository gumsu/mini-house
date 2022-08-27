package com.example.board.request;

import com.example.board.domain.post.Post;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class PostSaveRequest {

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;
    @NotBlank(message = "내용을 입력해주세요.")
    private String content;
    @NotBlank(message = "작성자를 입력해주세요.")
    private String writer;

    @Builder
    public PostSaveRequest(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .createdDate(LocalDateTime.now())
                .modifiedDate(null)
                .build();
    }
}
