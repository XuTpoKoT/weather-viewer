package com.weatherviewer.weather;

import lombok.Builder;

import java.time.LocalTime;


@Builder
public record HourlyForecastResponse(
    WeatherCondition weatherCondition,
    TimeOfDay timeOfDay,
    String description,
    Double temperature,
    LocalTime time) {
}
