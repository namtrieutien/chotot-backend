package com.sunny.Sunny.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sunny.Sunny.entity.Address;
import com.sunny.Sunny.entity.Role;
import com.sunny.Sunny.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter

public class UserDTO {
    private Integer id;

    private String name;

    private String email;

    private String phone;

    private Address address;

    @JsonIgnore
    private String password;

    private Set<Role> roles = new HashSet<>();

    public UserDTO() {
    }

    public UserDTO(Integer id, String name, String email,String password, String phone, Address address,Set<Role> roles ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.roles = roles;
    }

}

