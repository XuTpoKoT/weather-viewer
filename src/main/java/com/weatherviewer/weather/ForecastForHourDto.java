package com.weatherviewer.weather;

import lombok.Builder;

import java.time.LocalTime;


@Builder
public record ForecastForHourDto(
    String description,
    Double temperature,
    LocalTime time,
    String iconUrl) {
}
