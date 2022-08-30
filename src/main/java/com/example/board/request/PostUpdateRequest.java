package com.example.board.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PostUpdateRequest {

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;
    @NotBlank(message = "내용을 입력해주세요.")
    private String content;
    @NotBlank(message = "작성자를 입력해주세요.")
    private String writer;

    @Builder
    public PostUpdateRequest(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
}
