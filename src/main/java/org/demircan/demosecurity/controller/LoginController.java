package org.demircan.demosecurity.controller;

import org.demircan.demosecurity.models.dtos.UserLoginDto;
import org.demircan.demosecurity.security.LoggedUserModel;
import org.demircan.demosecurity.security.jwtsettings.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
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
        Authentication authenticate = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(userLoginDto.username(), userLoginDto.password()));
        String token = jwtUtil.generateToken(authenticate);
        LoggedUserModel loggedUserModel = (LoggedUserModel) authenticate.getPrincipal();
        map.put("token",token);
        map.put("user",loggedUserModel);
        return map;
    }
}
