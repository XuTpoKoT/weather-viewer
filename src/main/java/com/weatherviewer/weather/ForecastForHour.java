package com.weatherviewer.weather;

import lombok.Builder;

import java.time.LocalTime;


@Builder
public record ForecastForHour(
    String description,
    Double temperature,
    LocalTime time,
    String iconUrl) {
}
