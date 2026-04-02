package com.example.fluxcartoms.auth.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET = "fluxcartsecretkeyfluxcartsecretkey123";
    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractEmail(String token)
    {
        return getClaims(token).getSubject();
    }

    private Claims getClaims(String token) {
        return  Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}