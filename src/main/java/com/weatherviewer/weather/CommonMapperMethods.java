package com.weatherviewer.weather;

import com.weatherviewer.weatherapi.WeatherApiResponse;
import com.weatherviewer.weatherapi.model.Weather;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class CommonMapperMethods {
    @Named("description")
    public String description(List<Weather> weatherList) {
        return weatherList.get(0).getDescription();
    }
    @Named("time")
    public LocalTime time(LocalDateTime dateTime) {
        return dateTime.toLocalTime();
    }
    @Named("sunset")
    public LocalTime sunset(WeatherApiResponse.Sys sys) {
        return sys.getSunsetTime().toLocalTime();
    }
    @Named("sunrise")
    public LocalTime sunrise(WeatherApiResponse.Sys sys) {
        return sys.getSunriseTime().toLocalTime();
    }
}
