package com.weatherviewer.weather;

import lombok.Builder;

import java.time.LocalDate;


@Builder
public record ForecastForDay(
    WeatherCondition weatherCondition,
    TimeOfDay timeOfDay,
    Double temperature,
    LocalDate date) {
}
