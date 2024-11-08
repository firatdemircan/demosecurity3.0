package org.demircan.demosecurity.security;

import org.demircan.demosecurity.security.jwtsettings.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final MyAuthenticationProvider authenticationProvider;
    private final JwtFilter jwtFilter;
    private final MyProviderEntryPoint myProviderEntryPoint;
    private final MyAccessDeniedHandler myAccessDeniedHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable // Disable CSRF for demonstration; usually, keep it enabled
                )
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/user/login").permitAll()
                        .anyRequest().authenticated()// Secure all other requests
                ).exceptionHandling(entry ->
                        entry.authenticationEntryPoint(myProviderEntryPoint).accessDeniedHandler(myAccessDeniedHandler))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );
        return http.build();
    }
}
