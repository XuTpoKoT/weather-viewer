package com.weatherviewer.auth;

public class BadCredentialsException extends RuntimeException {
    public BadCredentialsException() { super(); }
    public BadCredentialsException(String errorMessage) {
        super(errorMessage);
    }
}
