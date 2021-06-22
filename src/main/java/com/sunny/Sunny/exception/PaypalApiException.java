package com.sunny.Sunny.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class PaypalApiException extends RuntimeException {
    public PaypalApiException() {
    }

    public PaypalApiException(String message) {
        super(message);
    }
}
