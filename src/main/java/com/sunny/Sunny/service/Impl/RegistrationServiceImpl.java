package com.sunny.Sunny.service.Impl;

import com.sunny.Sunny.common.ERole;
import com.sunny.Sunny.constants.AuthConstants;
import com.sunny.Sunny.constants.RouteConstants;
import com.sunny.Sunny.entity.*;
import com.sunny.Sunny.model.request.RegistrationRequest;
import com.sunny.Sunny.repository.AddressRepository;
import com.sunny.Sunny.repository.RoleRepository;
import com.sunny.Sunny.repository.UserRepository;
import com.sunny.Sunny.service.RegistrationService;
import com.sunny.Sunny.service.UserActivationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserActivationService userActivationService;

    @Autowired
    public JavaMailSender emailSender;

    @Value("${email.baseUrl}")
    private String baseUrl;

    @Override
    public User register(RegistrationRequest registrationRequest) {
        return signUpUser(registrationRequest);
    }

    @Override
    public User signUpUser(RegistrationRequest registrationRequest) {
        Boolean check = this.userRepository.existsByEmail(registrationRequest.getEmail());

        if (!check) {
            Address address = this.addressRepository.save(new Address(
                    registrationRequest.getCommute(),
                    registrationRequest.getDistrict(),
                    registrationRequest.getCity())
            );
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            Set<Role> roles = new HashSet<>();
            roles.add(userRole);
            User user = this.userRepository.save(new User(
                    registrationRequest.getName(),
                    registrationRequest.getEmail(),
                    registrationRequest.getPhone(),
                    passwordEncoder.encode(registrationRequest.getPassword()),
                    AuthConstants.NOT_ACTIVATED,
                    address
            ));
            user.setRoles(roles);

            //save verification token
            String token = UUID.randomUUID().toString();

            UserActivation userActivation = this.userActivationService.saveActivationToken(new UserActivation(
                    token,
                    LocalDateTime.now().plusMinutes(15),
                    null,
                    user
            ));

            //send email
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject("Activation Email");
            message.setText("Click heer to active your email: " + baseUrl + RouteConstants.ROUTE_CONFIRM + token);
            this.emailSender.send(message);

            return user;
        } else {
            throw new IllegalStateException("Email is exist");
        }
    }

    @Transactional
    @Override
    public String confirmToken(String token) {
        UserActivation userActivationToken = this.userActivationService.getByToken(token);

        if (userActivationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("Email already confirmed");
        }

        LocalDateTime expiredAt = userActivationToken.getExpiredAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token is expired");
        }

        this.userActivationService.setConfirmedAt(token);
        this.userRepository.activeUser(userActivationToken.getUser().getEmail());
        return "Your email is activated";
    }
}
