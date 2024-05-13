package com.weatherviewer.auth;

public class AccessForbiddenException extends RuntimeException {
    public AccessForbiddenException() { super(); }
    public AccessForbiddenException(String errorMessage) {
        super(errorMessage);
    }
}
