package com.dans.test.demo.exception;

import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

@Getter
public class CredentialsException extends AuthenticationException {
    private final String message;
    public CredentialsException(String message) {
        super(message);
        this.message = message;
    }
}
