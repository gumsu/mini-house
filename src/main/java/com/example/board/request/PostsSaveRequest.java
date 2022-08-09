package com.example.board.request;

import com.example.board.domain.post.Posts;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostsSaveRequest {

    private String title;
    private String content;
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
