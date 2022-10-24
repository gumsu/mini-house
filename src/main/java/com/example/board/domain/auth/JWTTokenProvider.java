package com.example.board.domain.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JWTTokenProvider implements InitializingBean {

    private static final long EXPIRE_TIME = 60 * 60 * 2 * 1000L; // 2시간
    public static final String ACCESS_TOKEN = "ACCESS-TOKEN";

    @Value("${jwt.secret-key}")
    private String secretKey;
    private Key encodedSecretKet;

    @Override
    public void afterPropertiesSet() {
        this.encodedSecretKet = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    private String createToken(Claims claims) {
        return Jwts.builder()
            .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
            .setClaims(claims)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
            .signWith(encodedSecretKet, SignatureAlgorithm.HS256)
            .compact();
    }

    public String generateToken(String userPk, String role) {
        Claims claims = Jwts.claims().setSubject(userPk);
        claims.put("role", role);
        return createToken(claims);
    }

    public String getUserPk(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(encodedSecretKet)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(encodedSecretKet)
                .build()
                .parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
