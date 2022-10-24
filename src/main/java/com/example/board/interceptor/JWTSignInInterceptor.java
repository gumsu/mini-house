package com.example.board.interceptor;

import com.example.board.domain.auth.JWTTokenProvider;
import com.example.board.exception.InvalidToken;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
@Component
public class JWTSignInInterceptor implements HandlerInterceptor {

    private final JWTTokenProvider jwtTokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String headerToken = request.getHeader(JWTTokenProvider.ACCESS_TOKEN);

        if (headerToken == null || headerToken.isBlank() || !jwtTokenProvider.validateToken(headerToken)) {
            throw new InvalidToken("토큰이 존재하지 않습니다.");
        }
        return true;
    }
}
