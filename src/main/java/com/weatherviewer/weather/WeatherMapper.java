package com.weatherviewer.weather;

import com.weatherviewer.weatherapi.WeatherApiResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = CommonMapperMethods.class)
public interface WeatherMapper {
    @Mapping(target = ".", source = "main")
    @Mapping(target = "description", source = "weatherList", qualifiedByName = "description")
    @Mapping(target = "iconUrl", source = "iconUrl")
    @Mapping(target = "windSpeed", source = "wind.speed")
    @Mapping(target = "windDirection", source = "wind.deg")
    @Mapping(target = "windGust", source = "wind.gust")
    @Mapping(target = "cloudiness", source = "clouds.cloudiness")
    @Mapping(target = "sunrise", source = "sys", qualifiedByName = "sunrise")
    @Mapping(target = "sunset", source = "sys", qualifiedByName = "sunset")
    WeatherResponse getWeatherResponse(WeatherApiResponse weatherApiResponse);
}
