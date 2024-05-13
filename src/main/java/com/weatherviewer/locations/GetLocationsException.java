package com.weatherviewer.locations;

public class GetLocationsException extends RuntimeException {
    public GetLocationsException() { super(); }
    public GetLocationsException(String errorMessage) {
        super(errorMessage);
    }
    public GetLocationsException(String message, Throwable cause) {
        super(message, cause);
    }
    public GetLocationsException(Throwable cause) {
        super(cause);
    }
}
