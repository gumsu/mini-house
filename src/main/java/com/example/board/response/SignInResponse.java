package com.example.board.response;

import com.example.board.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SignInResponse {
    private String token;
    private String refreshToken;
    private String memberName;
    private String memberEmail;

    public SignInResponse(Member entity, String token) {
        this.token = token;
        this.refreshToken = null;
        this.memberName = entity.getName();
        this.memberEmail = entity.getEmail();
    }
}
