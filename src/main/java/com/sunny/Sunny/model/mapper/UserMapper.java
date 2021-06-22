package com.sunny.Sunny.model.mapper;

import com.sunny.Sunny.entity.User;
import com.sunny.Sunny.model.dto.UserDTO;

public class UserMapper {

    public static UserDTO toUserDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getPhone(),
                user.getAddress(),
                user.getRoles());
    }


}