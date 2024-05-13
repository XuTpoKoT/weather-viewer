package com.weatherviewer.weatherapi;

public class GetWeatherException extends RuntimeException {
    public GetWeatherException() { super(); }
    public GetWeatherException(String errorMessage) {
        super(errorMessage);
    }
    public GetWeatherException(String message, Throwable cause) {
        super(message, cause);
    }
    public GetWeatherException(Throwable cause) {
        super(cause);
    }
}
