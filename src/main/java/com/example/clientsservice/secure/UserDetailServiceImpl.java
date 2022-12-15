package com.example.clientsservice.secure;

import com.example.clientsservice.models.User;
import com.example.clientsservice.services.data.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        System.out.println(user);
        if(user==null)
            throw new UsernameNotFoundException(username);
        return new UserDetailImpl(user);
    }

}
