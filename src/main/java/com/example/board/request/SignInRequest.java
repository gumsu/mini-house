package com.example.board.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SignInRequest {

    private String email;
    private String password;

    @Builder
    public SignInRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
