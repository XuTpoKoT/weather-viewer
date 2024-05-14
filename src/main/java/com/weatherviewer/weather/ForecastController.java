package com.weatherviewer.weather;

import com.weatherviewer.locations.Location;
import com.weatherviewer.locations.LocationNotFoundException;
import com.weatherviewer.locations.LocationRepo;
import com.weatherviewer.weatherapi.ForecastApiResponse;
import com.weatherviewer.weatherapi.WeatherApiService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/forecast")
@Slf4j
@RequiredArgsConstructor
public class ForecastController {
    private final WeatherApiService weatherApiService;
    private final LocationRepo locationRepo;
    private final WeatherMapper weatherMapper = Mappers.getMapper(WeatherMapper.class);
    private final DailyForecastMapper dailyForecastMapper = new DailyForecastMapper();
    @GetMapping
    public String getForecast(HttpSession session, @RequestParam("locationId") UUID locationId, Model model) {
        log.info("getForecast called with session id " + session.getAttribute("sessionId"));

        Location location = locationRepo.findById(locationId)
                .orElseThrow(() -> new LocationNotFoundException("Location: " + locationId + " is not found"));

        ForecastApiResponse forecastForLocation = weatherApiService.getForecastForLocation(location);
        List<ForecastForHour> hourlyForecast = weatherMapper.getHourlyForecast(forecastForLocation.getForecasts());
        List<ForecastForDay> dailyForecast = dailyForecastMapper.getDailyForecast(forecastForLocation.getForecasts());

        model.addAttribute("locationName", location.getName());
        model.addAttribute("hourlyForecast", hourlyForecast);
        model.addAttribute("dailyForecast", dailyForecast);

        return "forecast";
    }
}
