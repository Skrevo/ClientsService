package com.example.clientsservice.secure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.clientsservice.models.User.Role.ADMIN;
import static com.example.clientsservice.models.User.Role.USER;

@Configuration
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager inMemory(BCryptPasswordEncoder encoder) {
        return new InMemoryUserDetailsManager(
                User.builder()
                        .passwordEncoder(encoder::encode)
                        .username("u")
                        .password("u")
                        .roles(USER.name())
                        .build(),
                User.builder()
                        .passwordEncoder(encoder::encode)
                        .username("a")
                        .password("a")
                        .roles(ADMIN.name())
                        .build()
        );
    }
}