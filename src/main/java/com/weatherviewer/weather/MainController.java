package com.weatherviewer.weather;

import com.weatherviewer.locations.Location;
import com.weatherviewer.locations.LocationRepo;
import com.weatherviewer.weatherapi.WeatherApiResponse;
import com.weatherviewer.weatherapi.WeatherApiService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
@Slf4j
@RequiredArgsConstructor
public class MainController {
    private final LocationRepo locationRepo;
    private final WeatherApiService weatherApiService;
    private final WeatherMapper weatherMapper = Mappers.getMapper(WeatherMapper.class);
    @GetMapping
    public String getMainPage(HttpSession session, Model model) {
        log.info("getMainPage called with session id " + session.getAttribute("sessionId"));

        if (session.getAttribute("login") != null) {
            String login = session.getAttribute("login").toString();
            List<Location> userLocations = locationRepo.findByUsersLogin(login);

            log.info("Finding current weather for user locations");
            Map<Location, WeatherDto> locationWeatherMap = new HashMap<>();

            for (Location location : userLocations) {
                WeatherApiResponse weatherApiResponse = weatherApiService.getWeatherForLocation(location);
                locationWeatherMap.put(location, weatherMapper.getWeatherResponse(weatherApiResponse));
            }

            model.addAttribute("locationWeatherMap", locationWeatherMap);
        }

        return "home";
    }
}
