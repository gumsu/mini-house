package com.example.board.controller;

import com.example.board.domain.auth.JWTTokenProvider;
import com.example.board.request.SignInRequest;
import com.example.board.request.SignUpRequest;
import com.example.board.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public Long signUp(@RequestBody SignUpRequest signUpRequest) {
        return userService.signUp(signUpRequest);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<HttpStatus> signIn(@RequestBody SignInRequest signInRequest) {
        String token = userService.signIn(signInRequest).getToken();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTTokenProvider.ACCESS_TOKEN, token);
        return ResponseEntity.ok().headers(httpHeaders).body(HttpStatus.OK);
    }

    @GetMapping("/sign-out")
    public ResponseEntity<HttpStatus> signOut() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTTokenProvider.ACCESS_TOKEN, "");
        return ResponseEntity.ok().headers(httpHeaders).body(HttpStatus.OK);
    }
}
