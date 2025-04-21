package com.db.db_server.core.component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final Key secretKey;
    private final long tokenValidityInMilliseconds = 1000 * 60 * 60; // 1시간

    public JwtTokenProvider(@Value("${jwt.secret}") String secret) {
        // jwt.secret 값을 application.properties에서 가져옴
        // 가져온 값을 byte[]로 변환하여 Key 객체를 생성
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String email){
        Claims claims = Jwts.claims().setSubject(email);
        Date now = new Date();
        Date expiry = new Date(now.getTime() + tokenValidityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // 토큰에서 Claims 꺼내기
    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 토큰에서 이메일 꺼내기
    public String getEmailFromToken(String token) {
        return getClaims(token).getSubject();
    }

    // 유효한 토큰인지 확인
    public boolean validateToken(String token) {
        try {
            getClaims(token); // 파싱되면 유효함
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
