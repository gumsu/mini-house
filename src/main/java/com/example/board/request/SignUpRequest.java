package com.example.board.request;

import com.example.board.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class SignUpRequest {

    private String name;
    private String email;
    private String password;

    @Builder
    public SignUpRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .createdAt(LocalDateTime.now())
                .role("USER")
                .build();
    }
}
