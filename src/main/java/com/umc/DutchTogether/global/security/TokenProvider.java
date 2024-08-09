package com.umc.DutchTogether.global.security;

import com.umc.DutchTogether.domain.meeting.entity.Meeting;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Service
public class TokenProvider {

    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String createToken(Meeting meeting) {
        Date expiryDate = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));
        return Jwts.builder()
                .signWith(secretKey)  // 생성된 비밀 키로 서명
                .setSubject(meeting.getMeetingId().toString())
                .setIssuer("demo app")
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .compact();
    }

    public String validateAndGetUserId(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}