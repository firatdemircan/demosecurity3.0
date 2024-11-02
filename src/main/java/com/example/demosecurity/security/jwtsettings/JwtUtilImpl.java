package com.example.demosecurity.security.jwtsettings;

import com.example.demosecurity.security.LoggedUserModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtUtilImpl implements JwtUtil {
    @Override
    public String generateToken(Authentication authentication) throws JsonProcessingException {
        return "";
    }

    @Override
    public LoggedUserModel getAuthenticationFromToken(String token) throws JsonProcessingException {
        return null;
    }

    @Override
    public boolean validateToken(String token) throws JsonProcessingException {
        return false;
    }

    @Override
    public String refreshToken() throws JsonProcessingException {
        return "";
    }

    @Override
    public String generateToken(LoggedUserModel logged) throws JsonProcessingException {
        return "";
    }
}
