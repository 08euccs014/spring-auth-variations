package com.a3logics.demo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String generateToken(String username, String email, Long userId) {

        Instant now = Instant.now();

        String jwtToken = Jwts.builder()
                .claim("username", username)
                .claim("email", email)
                .setSubject(String.valueOf(userId))
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(5L, ChronoUnit.MINUTES)))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();

        return jwtToken;
    }

    public Boolean verifyToken(String token) {
        JwtParser parser = Jwts.parser().setSigningKey(jwtSecret);

        if(parser.isSigned(token)) {

            Claims claims = getClaims(token);

            return Instant.now().compareTo(claims.getExpiration().toInstant()) < 0;
        };

        return false;
    }

    public Claims getClaims(String token) {
        JwtParser parser = Jwts.parser().setSigningKey(jwtSecret);

        Claims claims = parser.parseClaimsJws(token).getBody();

        return claims;
    }

    public String getUsername(String token) {
        Claims claims = getClaims(token);
        return claims.get("username").toString();
    }
}
