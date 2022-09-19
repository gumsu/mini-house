package com.example.board.service;

import com.example.board.repository.MemberRepository;
import com.example.board.request.MemberSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public Long register(MemberSaveRequest request) {
        return memberRepository.save(request.toEntity()).getId();
    }
}
