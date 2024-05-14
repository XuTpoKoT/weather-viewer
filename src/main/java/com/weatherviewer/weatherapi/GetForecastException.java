package com.weatherviewer.weatherapi;

public class GetForecastException extends RuntimeException {
    public GetForecastException(String errorMessage) {
        super(errorMessage);
    }
}
