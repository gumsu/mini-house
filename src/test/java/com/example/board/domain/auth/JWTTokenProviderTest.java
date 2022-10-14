package com.example.board.domain.auth;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class JWTTokenProviderTest {

    final JWTTokenProvider jwtTokenProvider = new JWTTokenProvider();

    @Test
    void 토큰_생성() {
        String token = jwtTokenProvider.generateToken("username", "USER");
        assertThat(token).isNotNull();
    }

    @Test
    void 토큰_검증() {
        String token = jwtTokenProvider.generateToken("username", "USER");
        boolean validateToken = jwtTokenProvider.validateToken(token);
        assertThat(validateToken).isTrue();
    }

    @Test
    void 잘못된_토큰은_에러() {
        String wrongToken = "aaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        boolean validateToken = jwtTokenProvider.validateToken(wrongToken);
        assertThat(validateToken).isFalse();
    }

    @Test
    void 만료된_토큰은_에러() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VybmFtZSIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNjY1NzMzNjYxLCJleHAiOjE2NjU3NDA4NjF9.ry3HrnthQAin_A_vfAV6SR0QSHpCRRX7SrL8SYYQVwU";
        boolean validateToken = jwtTokenProvider.validateToken(token);
        assertThat(validateToken).isFalse();
    }

    @Test
    void 토큰에서_유저_정보_가져오기() {
        String token = jwtTokenProvider.generateToken("username", "USER");
        String userPk = jwtTokenProvider.getUserPk(token);
        assertThat(userPk).isEqualTo("username");
    }
}