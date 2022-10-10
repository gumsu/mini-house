package com.example.board.service;

import com.example.board.domain.auth.JWTTokenProvider;
import com.example.board.domain.member.Member;
import com.example.board.repository.MemberRepository;
import com.example.board.request.MemberSaveRequest;
import com.example.board.request.SignInRequest;
import com.example.board.response.SignInResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final JWTTokenProvider jwtTokenProvider;

    public Long register(MemberSaveRequest request) {
        return memberRepository.save(request.toEntity()).getId();
    }

    public SignInResponse signIn(SignInRequest request) {
        Member member = memberRepository.findByEmail(request.getEmail().toLowerCase())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 email 입니다."));
        String token = jwtTokenProvider.generateToken(member.getUsername(), member.getRoles());
        return new SignInResponse(member, token);
    }
}
