package com.sunny.Sunny.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class RequestLogin {
        @NotBlank(message = "Email is required")
        @Email(message = "Please provide a valid email")
        private String email;

        @NotBlank(message = "Password is required")
        private String password;

        public RequestLogin(String username, String password) {
            this.email = username;
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setUsername(String username) {
            this.email = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
