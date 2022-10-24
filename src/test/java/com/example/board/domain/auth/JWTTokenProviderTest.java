package com.example.board.domain.auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.board.exception.InvalidToken;
import java.lang.reflect.Field;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {"spring.config.location=classpath:application-dev.yml"})
class JWTTokenProviderTest {

    @Autowired
    Environment environment;

    @Autowired
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
        assertThatThrownBy(() -> jwtTokenProvider.validateToken(wrongToken))
            .isInstanceOf(InvalidToken.class).hasMessageContaining("유효하지 않은 구성의 토큰입니다.");
    }

    @Test
    void 만료된_토큰은_에러() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Class JWTTokenProviderClass = Class.forName("com.example.board.domain.auth.JWTTokenProvider");
        Field field = JWTTokenProviderClass.getDeclaredField("expireTime");
        field.setAccessible(true);
        field.set(jwtTokenProvider, 0);

        String token = jwtTokenProvider.generateToken("username", "USER");
        assertThatThrownBy(() -> jwtTokenProvider.validateToken(token))
            .isInstanceOf(InvalidToken.class).hasMessageContaining("기한이 만료된 토큰입니다.");
    }

    @Test
    void 토큰에서_유저_정보_가져오기() {
        String token = jwtTokenProvider.generateToken("username", "USER");
        String userPk = jwtTokenProvider.getUserPk(token);
        assertThat(userPk).isEqualTo("username");
    }
}
