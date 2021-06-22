package com.sunny.Sunny.service.Impl;

import com.sunny.Sunny.entity.User;
import com.sunny.Sunny.model.dto.UserDTO;
import com.sunny.Sunny.repository.UserRepository;
import com.sunny.Sunny.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetailsImpl loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = findByEmail(email);
        return UserDetailsImpl.build(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email '"+ email+ "' Not Found" ));
    }

}
