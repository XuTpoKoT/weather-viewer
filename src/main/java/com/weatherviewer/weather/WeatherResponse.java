package com.weatherviewer.weather;

import lombok.Builder;

import java.time.LocalTime;


@Builder
public record WeatherResponse(
    WeatherCondition weatherCondition,
    TimeOfDay timeOfDay,
    String description,
    Double temperature,
    Double temperatureFeelsLike,
    Double temperatureMinimum,
    Double temperatureMaximum,
    Integer humidity,
    Integer pressure,
    Double windSpeed,
    Integer windDirection,
    Double windGust,
    Integer cloudiness,
    LocalTime curTime,
    LocalTime sunrise,
    LocalTime sunset) {
}
