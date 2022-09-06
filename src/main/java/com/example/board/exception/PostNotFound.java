package com.example.board.exception;

public class PostNotFound extends BaseException {

    private static final String MESSAGE = "게시물이 존재하지 않습니다";

    public PostNotFound() {
        super(MESSAGE);
    }

    public PostNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
