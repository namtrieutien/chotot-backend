package com.sunny.Sunny.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class RegistrationRequest {

    private final String name;

    private final String email;

    private final String password;

    private final String phone;

    private final String commute;

    private final String district;

    private final String city;

}
