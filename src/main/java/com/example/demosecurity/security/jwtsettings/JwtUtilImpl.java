package com.example.demosecurity.security.jwtsettings;

import com.example.demosecurity.security.LoggedUserModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtilImpl implements JwtUtil {

    private final ObjectMapper objectMapper;
    Key key = Keys.hmacShaKeyFor("kjasdhfjkashdfkjashdfkjashjkdfhaskdjfhksajdhfkasdhfaksjdf".getBytes(StandardCharsets.UTF_8));


    // Generate JWT token
    @Override
    public String generateToken(LoggedUserModel loggedUserModel) {

        return Jwts.builder()
                .setSubject(loggedUserModel.getUsername())
                .signWith(key, SignatureAlgorithm.HS256)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .compact();
    }

    @Override
    public String generateToken(Authentication authentication) throws JsonProcessingException {

        System.out.println(authentication);
        return Jwts.builder()
                .setSubject(objectMapper.writeValueAsString(authentication.getPrincipal()))
                .signWith(key, SignatureAlgorithm.HS256)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .compact();
    }


    @Override
    public LoggedUserModel getAuthenticationFromToken(String token) throws JsonProcessingException {
        return objectMapper.readValue(extractAllClaims(token).getSubject(), LoggedUserModel.class);
    }

    // Validate JWT token
    @Override
    public boolean validateToken(String token) {
        try {
            return (!isTokenExpired(token));
        } catch (JwtException e) {
            return false; // Invalid token
        }
    }

    @Override
    public String refreshToken() throws JsonProcessingException {
        return "";
    }

    // Extract username from JWT token
    public LoggedUserModel extractUsername(String token) throws IOException {
        System.out.println("token claims :" + extractAllClaims(token).getSubject());


        return objectMapper.readValue(extractAllClaims(token).getSubject(), LoggedUserModel.class);
    }

    // Check if the token is expired
    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    // Extract all claims from the token
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
