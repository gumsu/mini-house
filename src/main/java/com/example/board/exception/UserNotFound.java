package com.example.board.exception;

public class UserNotFound extends BaseException {

    private static final String MESSAGE = "사용자가 존재하지 않습니다";

    public UserNotFound() {
        super(MESSAGE);
    }

    public UserNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
