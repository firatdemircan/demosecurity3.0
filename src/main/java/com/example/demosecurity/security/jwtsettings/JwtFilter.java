package com.example.demosecurity.security.jwtsettings;

import com.example.demosecurity.security.LoggedUserModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Get token from reuest header and parse it


        String token = parseToken(request);

        if (token != null) {
            //then validate token and give it to username and password authentication token
            LoggedUserModel authenticationFromToken = jwtUtil.getAuthenticationFromToken(token);

            try {
                if (jwtUtil.validateToken(token)) {
                    LoggedUserModel authenticationFromToken1 = jwtUtil.getAuthenticationFromToken(token);
                }
            } catch (JsonProcessingException e) {
                throw new IOException("user not found");
            }

            if(authenticationFromToken!=null) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(authenticationFromToken, "", Arrays.asList(new SimpleGrantedAuthority(authenticationFromToken.getRole())));
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        }


    }

    private String parseToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }
}
