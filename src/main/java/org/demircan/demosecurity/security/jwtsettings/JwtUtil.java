package org.demircan.demosecurity.security.jwtsettings;

import org.demircan.demosecurity.security.LoggedUserModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.core.Authentication;

public interface JwtUtil {
     String generateToken(Authentication authentication) throws JsonProcessingException;

    LoggedUserModel getAuthenticationFromToken(String token) throws JsonProcessingException;

    boolean validateToken(String token) throws JsonProcessingException;

    String refreshToken() throws JsonProcessingException;

    String generateToken(LoggedUserModel logged) throws JsonProcessingException;
}
