package com.example.board.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import static java.lang.Math.max;
import static java.lang.Math.min;

@Getter
@Setter
@Builder
public class PostSearchRequest {

    private static final int MAX_SIZE = 2000;
    private static final int MIN_SIZE = 1;

    @Builder.Default
    private Integer page = 1;
    @Builder.Default
    private Integer size = 5;

    public long getOffset() {
        return (long) (max(MIN_SIZE, page) - 1) * min(MAX_SIZE, size);
    }
}
