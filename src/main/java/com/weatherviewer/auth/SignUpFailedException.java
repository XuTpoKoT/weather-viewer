package com.weatherviewer.auth;

public class SignUpFailedException extends RuntimeException {
    public SignUpFailedException() { super(); }
    public SignUpFailedException(String errorMessage) {
        super(errorMessage);
    }
    public SignUpFailedException(String message, Throwable cause) {
        super(message, cause);
    }
    public SignUpFailedException(Throwable cause) {
        super(cause);
    }
}
