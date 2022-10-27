package com.example.board.exception;

public class InvalidToken extends BaseException{

    private static final String MESSAGE = "인증 토큰이 유효하지 않습니다.";

    public InvalidToken() {
        super(MESSAGE);
    }

    public InvalidToken(String message) {
        super(message);
    }

    public InvalidToken(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return 401;
    }
}
