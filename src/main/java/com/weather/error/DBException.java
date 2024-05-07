package com.weather.error;

public class DBException extends RuntimeException {
    public DBException(Throwable cause) {
        super(cause);
    }
}
