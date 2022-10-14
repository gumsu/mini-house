package com.example.board.interceptor;

import com.example.board.domain.auth.JWTTokenProvider;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
@Component
public class JWTSignInInterceptor implements HandlerInterceptor {

    @Autowired
    private JWTTokenProvider jwtTokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String headerToken = request.getHeader("Authorization");

        if (headerToken == null || !jwtTokenProvider.validateToken(headerToken)) {
            throw new RuntimeException("인증 토큰이 없습니다.");
        }
        return true;
    }
}
