package com.example.clientsservice.secure;

import com.example.clientsservice.models.User;
import com.example.clientsservice.repositories.UserRepository;
import com.example.clientsservice.services.data.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//@Component
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.err.println("Auth");
        User user = userService.findByUsername(authentication.getName());
        System.err.println(user);
        if (user != null)
            throw  new BadCredentialsException("wrong username");
        if (passwordEncoder.matches(
                    (String)authentication.getCredentials(),
                    user.getPassword()))
                throw new BadCredentialsException("wrong password");
        return new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(),
                List.of(new SimpleGrantedAuthority(user.getRole().name()))
                );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
