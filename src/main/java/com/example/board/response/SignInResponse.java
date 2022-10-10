package com.example.board.response;

import com.example.board.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SignInResponse {
    private String token;
    private String refreshToken;
    private String memberName;
    private String memberEmail;

    public SignInResponse(User entity, String token) {
        this.token = token;
        this.refreshToken = null;
        this.memberName = entity.getName();
        this.memberEmail = entity.getEmail();
    }
}
