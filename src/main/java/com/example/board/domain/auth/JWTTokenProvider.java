package com.example.board.domain.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTTokenProvider {

    private static final long EXPIRE_TIME = 60 * 60 * 2 * 1000L; // 2시간
    private static final String SECRET_KEY = "member-secret-key-aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

    private String createToken(Map<String, String> claims) {
        String secretKey = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRE_TIME))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String generateToken(String email) {
        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        return createToken(map);
    }
}
