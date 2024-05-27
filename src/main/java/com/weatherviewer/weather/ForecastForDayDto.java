package com.weatherviewer.weather;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ForecastForDayDto(
    WeatherCondition weatherCondition,
    Double temperature,
    LocalDate date,
    String iconUrl) {
}
