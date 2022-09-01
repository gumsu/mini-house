package com.example.board.exception;

public class PostNotFound extends RuntimeException {

    private static final String MESSAGE = "게시물이 존재하지 않습니다";

    public PostNotFound() {
        super(MESSAGE);
    }
}
