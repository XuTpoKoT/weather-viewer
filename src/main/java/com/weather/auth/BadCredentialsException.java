package com.weather.auth;

public class BadCredentialsException extends RuntimeException {
    public BadCredentialsException() { super(); }
    public BadCredentialsException(String errorMessage) {
        super(errorMessage);
    }
}
