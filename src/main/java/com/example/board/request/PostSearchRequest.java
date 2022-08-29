package com.example.board.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostSearchRequest {

    private int page;
    private int size;

    @Builder
    public PostSearchRequest(int page, int size) {
        this.page = page;
        this.size = size;
    }
}
