package com.weatherviewer.auth;

public class SignUpFailedException extends RuntimeException {
    public SignUpFailedException(String errorMessage) {
        super(errorMessage);
    }
}
