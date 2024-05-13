package com.weatherviewer.weather;

import com.weatherviewer.weatherapi.Weather;
import com.weatherviewer.weatherapi.WeatherApiResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Mapper
public interface WeatherResponseMapper {

    @Mapping(target = ".", source = "main")
    @Mapping(target = "description", source = "weatherList", qualifiedByName = "description")
    @Mapping(target = "weatherCondition", source = "weatherList", qualifiedByName = "weatherCondition")
    @Mapping(target = "timeOfDay", source = "dateTime", qualifiedByName = "timeOfDay")
    @Mapping(target = "windSpeed", source = "wind.speed")
    @Mapping(target = "windDirection", source = "wind.deg")
    @Mapping(target = "windGust", source = "wind.gust")
    @Mapping(target = "cloudiness", source = "clouds.cloudiness")
    @Mapping(target = "curTime", source = "dateTime", qualifiedByName = "curTime")
    @Mapping(target = "sunrise", source = "sys", qualifiedByName = "sunrise")
    @Mapping(target = "sunset", source = "sys", qualifiedByName = "sunset")
    WeatherResponse fromWeatherApiResponse(WeatherApiResponse weatherApiResponse);
    @Named("description")
    default String description(List<Weather> weatherList) {
        return weatherList.get(0).getDescription();
    }
    @Named("weatherCondition")
    default WeatherCondition weatherCondition(List<Weather> weatherList) {
        return WeatherCondition.getWeatherConditionForCode(weatherList.get(0).getId());
    }
    @Named("timeOfDay")
    default TimeOfDay timeOfDay(LocalDateTime dateTime) {
        return TimeOfDay.getTimeOfDayForTime(dateTime);
    }
    @Named("curTime")
    default LocalTime curTime(LocalDateTime dateTime) {
        return dateTime.toLocalTime();
    }
    @Named("sunset")
    default LocalTime sunset(WeatherApiResponse.Sys sys) {
        return sys.getSunsetTime().toLocalTime();
    }
    @Named("sunrise")
    default LocalTime sunrise(WeatherApiResponse.Sys sys) {
        return sys.getSunriseTime().toLocalTime();
    }
}