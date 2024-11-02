package com.example.demosecurity.security;

import com.example.demosecurity.models.entity.UserEntity;
import com.example.demosecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MyAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userRepository;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        //this means its a new call
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        Optional<UserEntity> byUsername = userRepository.findByUsername(username);
        if (byUsername.isPresent()) {
            if (byUsername.get().getPassword().equals(password)) {
                LoggedUserModel loggedUserModel = new LoggedUserModel(username, password);
                return new UsernamePasswordAuthenticationToken(loggedUserModel, "");
            }
        } else {
            throw new BadCredentialsException("Invalid username or password");
        }


        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
