package com.example.board.domain.post;

import lombok.Builder;
import lombok.Getter;

/**
 * post 에서 수정할 수 있는 필드들에 대해서만 모음
 */
@Getter
public class PostEditor {

    private String title;
    private String content;

    @Builder
    public PostEditor(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
