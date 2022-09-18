package com.example.board.request;

import com.example.board.domain.member.Member;
import lombok.Builder;

import java.time.LocalDateTime;

public class MemberSaveRequest {

    private String name;
    private String email;
    private String password;

    @Builder
    public MemberSaveRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .email(email)
                .password(password)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
