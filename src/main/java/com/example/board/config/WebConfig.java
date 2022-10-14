package com.example.board.config;

import com.example.board.interceptor.JWTSignInInterceptor;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final JWTSignInInterceptor jwtSignInInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtSignInInterceptor)
            .addPathPatterns("/api/**")
            .excludePathPatterns(Arrays.asList("/api/**/user/**"));
    }
}
