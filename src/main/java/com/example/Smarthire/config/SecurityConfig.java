package com.example.Smarthire.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())   // disable csrf
                .authorizeHttpRequests(auth -> auth
                        //.requestMatchers("/api/user/**").permitAll()
                        //.anyRequest().authenticated()
                        .anyRequest().permitAll()
                );

        return http.build();
    }
}