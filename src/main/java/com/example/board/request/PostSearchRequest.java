package com.example.board.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostSearchRequest {

    @Builder.Default
    private Integer page = 1;
    @Builder.Default
    private Integer size = 5;

}
