package com.weatherviewer.weather;

import com.weatherviewer.weatherapi.ForecastApiResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = CommonMapperMethods.class)
public interface HourlyForecastMapper {
    @Mapping(target = "description", source = "weatherList", qualifiedByName = "description")
    @Mapping(target = "iconUrl", source = "iconUrl")
    @Mapping(target = "time", source = "dateTime", qualifiedByName = "time")
    @Mapping(target = "temperature", source = "main.temperature")
    ForecastForHourDto getForecastForHour(ForecastApiResponse.ForecastForHour forecastForHour);
    List<ForecastForHourDto> getHourlyForecast(List<ForecastApiResponse.ForecastForHour> forecastForHours);
}
