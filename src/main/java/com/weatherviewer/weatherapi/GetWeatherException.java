package com.weatherviewer.weatherapi;

public class GetWeatherException extends RuntimeException {
    public GetWeatherException(String errorMessage) {
        super(errorMessage);
    }
}
