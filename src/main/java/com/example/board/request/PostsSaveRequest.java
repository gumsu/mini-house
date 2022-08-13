package com.example.board.request;

import com.example.board.domain.post.Posts;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostsSaveRequest {

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;
    @NotBlank(message = "내용을 입력해주세요.")
    private String content;
    @NotBlank(message = "작성자를 입력해주세요.")
    private String writer;

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .createdDate(LocalDateTime.now())
                .modifiedDate(null)
                .build();
    }
}
