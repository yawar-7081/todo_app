package com.yawar.todo.todo_application.service;

import com.yawar.todo.todo_application.entity.User;
import com.yawar.todo.todo_application.utils.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String jwtSecretKey;

    private Key getSecretKey() {
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(CustomUserDetails user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("user_id", user.getUser().getId())
                .signWith(getSecretKey())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10))
                .compact();
    }

    public Long getUserIdFromToken(String token) {
        return Long.valueOf(Jwts.parser()
                                    .verifyWith((SecretKey) getSecretKey())
                                    .build()
                                    .parseSignedClaims(token)
                                    .getPayload().get("user_id").toString());
    }


}
