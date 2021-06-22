package com.sunny.Sunny.service.Impl;

import com.sunny.Sunny.entity.UserActivation;
import com.sunny.Sunny.repository.UserActivationRepository;
import com.sunny.Sunny.service.UserActivationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserActivationServiceImpl implements UserActivationService {

    @Autowired
    private UserActivationRepository userActivationRepository;

    @Override
    public UserActivation saveActivationToken(UserActivation userActivation) {
        return this.userActivationRepository.save(userActivation);
    }

    @Override
    public UserActivation getByToken(String token) {
        return this.userActivationRepository.getByToken(token).orElseThrow(
                () -> new IllegalStateException("token not found"));
    }

    @Override
    public int setConfirmedAt(String token) {
        return this.userActivationRepository.setConfirmAt(token, LocalDateTime.now());
    }
}
