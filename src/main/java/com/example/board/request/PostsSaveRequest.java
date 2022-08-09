package com.example.board.request;

import com.example.board.domain.post.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostsSaveRequest {

    private String title;
    private String content;
    private String writer;

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .build();
    }
}
