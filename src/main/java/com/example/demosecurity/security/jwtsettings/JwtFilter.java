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
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Get token from reuest header and parse it


        String token = parseToken(request);
        LoggedUserModel authenticationFromToken=null;
        if (token != null) {
            //then validate token and give it to username and password authentication token

            try {
                authenticationFromToken = jwtUtil.getAuthenticationFromToken(token);

            } catch (JsonProcessingException e) {
                throw new IOException("user not found");
            }

            if(authenticationFromToken!=null) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(authenticationFromToken, "", List.of(new SimpleGrantedAuthority(authenticationFromToken.getRole())));
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        }

        filterChain.doFilter(request,response);



    }

    private String parseToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }
}
