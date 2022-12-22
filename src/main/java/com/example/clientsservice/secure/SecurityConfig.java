package com.example.clientsservice.secure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

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

    @Bean
    public AuthenticationManager authManager(HttpSecurity security,
                                             BCryptPasswordEncoder passwordEncoder,
                                             UserDetailsService userDetailsService) throws Exception {
        System.err.println("authManager");
        return security
        .getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and().build();

    }

   // @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return customizer->
                customizer.debug(false)
                        .ignoring()
                        //.anyRequest();
                        .antMatchers("/css/**")
                        .antMatchers("/registration")
                        .mvcMatchers(HttpMethod.POST, "/registration");
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
         httpSecurity
                .authorizeRequests()
                .antMatchers(
                        "/error",
                        "/registration"
                )
                .permitAll()
                //
                .antMatchers(
                        "/",
                        "clients"
                )
                .authenticated()
                //
                .antMatchers(
                        "/users"
                )
                .hasAnyAuthority(
                        ADMIN.name()
                )
                //
                 .and()
                 .formLogin()
                 .loginPage(
                         "/authorization"
                 )
                 //
                 .and()
                 .logout()
                 //.logoutUrl("j_spring_security_logout")
                 .logoutSuccessUrl(
                         "/authorization"
                 )
                ;
        return httpSecurity.build();
    }
}