package com.example.board.controller;

import com.example.board.request.MemberSaveRequest;
import com.example.board.request.SignInRequest;
import com.example.board.response.SignInResponse;
import com.example.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/v1/member")
@RestController
public class MemberController {
    private final MemberService memberService;

    @PostMapping()
    public Long save(@RequestBody MemberSaveRequest memberSaveRequest) {
        return memberService.register(memberSaveRequest);
    }

    @PostMapping("/signin")
    public SignInResponse signIn(@RequestBody SignInRequest signInRequest) {
        return memberService.signIn(signInRequest);
    }
}
