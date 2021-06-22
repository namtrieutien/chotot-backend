package com.sunny.Sunny.service;

import com.sunny.Sunny.entity.User;
import com.sunny.Sunny.model.request.RegistrationRequest;

public interface RegistrationService {

    User register(RegistrationRequest registrationRequest);

    User signUpUser(RegistrationRequest registrationRequest);

    String confirmToken(String token);
}
