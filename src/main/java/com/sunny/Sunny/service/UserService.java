package com.sunny.Sunny.service;

import com.sunny.Sunny.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

    @Override
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;

    User findByEmail(String email);
}