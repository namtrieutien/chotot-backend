package com.sunny.Sunny.service;

import com.sunny.Sunny.entity.UserActivation;

public interface UserActivationService {

    UserActivation saveActivationToken(UserActivation userActivation);

    UserActivation getByToken(String token);

    int setConfirmedAt(String token);
}
