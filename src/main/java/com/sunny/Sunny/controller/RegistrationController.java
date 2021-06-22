package com.sunny.Sunny.controller;

import com.sunny.Sunny.entity.User;
import com.sunny.Sunny.model.mapper.UserMapper;
import com.sunny.Sunny.model.request.RegistrationRequest;
import com.sunny.Sunny.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/register")
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @PostMapping("")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest registrationRequest) {
        User data = this.registrationService.register(registrationRequest);
        return ResponseEntity.ok(UserMapper.toUserDTO(data));
    }

    @GetMapping("/confirm")
    public ResponseEntity<?> confirm(@RequestParam("token") String token) {
        return ResponseEntity.ok(this.registrationService.confirmToken(token));
    }
}
