package com.sunny.Sunny.model.response;

import com.sunny.Sunny.model.dto.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String expired_date;
    private UserDTO user;

    public JwtResponse(String accessToken, UserDTO user, String expired_date) {
        this.token = accessToken;
        this.user = user;
        this.expired_date = expired_date;
    }
}
