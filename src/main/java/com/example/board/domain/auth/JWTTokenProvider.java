package com.example.board.domain.auth;

import com.example.board.exception.InvalidToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
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

    public static final String ACCESS_TOKEN = "ACCESS-TOKEN";

    @Value("${jwt.expire-time}")
    private long expireTime;

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
            .setExpiration(new Date(System.currentTimeMillis() + expireTime))
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
        } catch (SecurityException e) {
            throw new InvalidToken("토큰의 서명을 확인하지 못했습니다.");
        } catch (MalformedJwtException e) {
            throw new InvalidToken("유효하지 않은 구성의 토큰입니다.");
        } catch (ExpiredJwtException e) {
            throw new InvalidToken("기한이 만료된 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            throw new InvalidToken("지원하지 않는 형식이나 구성의 토큰입니다.");
        } catch (IllegalArgumentException e) {
            throw new InvalidToken();
        }
    }
}
