package org.demircan.demosecurity.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
@EnableMethodSecurity
public class ConfigBeans {


    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
