package com.weatherviewer.weather;

import com.weatherviewer.weatherapi.ForecastApiResponse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DailyForecastMapper {
    public List<ForecastForDay> getDailyForecast(List<ForecastApiResponse.ForecastForHour> forecastForHours) {
        List<ForecastForDay>  dailyForecasts = new ArrayList<>();

        LocalDate currentDay = LocalDate.from(forecastForHours.get(0).getDateTime());
        LocalDate lastDay = LocalDate.from(forecastForHours.get(forecastForHours.size() - 1).getDateTime());

        while (currentDay.isBefore(lastDay)) {
            dailyForecasts.add(getForecastForDay(currentDay, forecastForHours));
            currentDay = currentDay.plusDays(1);
        }

        return dailyForecasts;
    }
    private ForecastForDay getForecastForDay(LocalDate date, List<ForecastApiResponse.ForecastForHour>
            hourlyForecast) {
        List<ForecastApiResponse.ForecastForHour> hourlyForecastForDay = hourlyForecast
                .stream()
                .filter(forecast -> forecast.getDateTime().toLocalDate().isEqual(date))
                .toList();

        return ForecastForDay.builder()
                .date(date)
                .temperature(getAvgTemperature(hourlyForecastForDay))
                .weatherCondition(getAvgWeather(hourlyForecastForDay))
                .iconUrl(hourlyForecastForDay.get(0).getIconUrl())
                .build();
    }
    private Double getAvgTemperature(List<ForecastApiResponse.ForecastForHour> hourlyForecastForDay) {
        return hourlyForecastForDay
                .stream()
                .map(forecast -> forecast.getMain().getTemperature())
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(Double.NaN);
    }

    private WeatherCondition getAvgWeather(List<ForecastApiResponse.ForecastForHour> hourlyForecastForDay) {
        return hourlyForecastForDay
                .stream()
                .map(forecast -> forecast.getWeatherList().get(0).getId())
                .map(WeatherCondition::getWeatherConditionForCode)
                .collect(Collectors.groupingBy(
                        weatherCondition -> weatherCondition,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(WeatherCondition.UNDEFINED);
    }
}
