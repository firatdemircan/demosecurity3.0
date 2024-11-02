package com.example.demosecurity.controller;

import com.example.demosecurity.models.dtos.UserLoginDto;
import com.example.demosecurity.security.LoggedUserModel;
import com.example.demosecurity.security.jwtsettings.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationProvider authenticationProvider;
    private final JwtUtil jwtUtil;

    @PostMapping("login")
    public Map<String,Object> login(@RequestBody UserLoginDto userLoginDto) throws JsonProcessingException {
        Map<String,Object> map = new HashMap<>();
        LoggedUserModel loggedUserModel = (LoggedUserModel) authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(userLoginDto.username(), userLoginDto.password()));
        String token = jwtUtil.generateToken(loggedUserModel);
        map.put("token",token);
        map.put("user",loggedUserModel);
        return map;
    }
}
