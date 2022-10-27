package com.example.board.response;

import com.example.board.domain.post.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Long views;
    private int like;

    public PostResponse(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.createdDate = entity.getCreatedAt();
        this.modifiedDate = entity.getModifiedAt();
        this.views = entity.getViews();
        this.like = entity.getLikes();
    }
}
