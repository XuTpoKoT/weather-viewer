package com.weatherviewer.locations;

public class GetLocationsException extends RuntimeException {
    public GetLocationsException(String message, Throwable cause) {
        super(message, cause);
    }
    public GetLocationsException(String message) {
        super(message);
    }
}
