package com.weatherviewer.weather;

import com.weatherviewer.auth.AccessForbiddenException;
import com.weatherviewer.auth.Session;
import com.weatherviewer.auth.SessionRepo;
import com.weatherviewer.auth.User;
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
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Controller
@RequestMapping("/")
@Slf4j
@RequiredArgsConstructor
public class MainController {
    private final SessionRepo sessionRepo;
    private final LocationRepo locationRepo;
    private final WeatherApiService weatherApiService;
    private final WeatherMapper weatherMapper = Mappers.getMapper(WeatherMapper.class);
    @GetMapping
    public String getMainPage(HttpSession httpSession, Model model) {
        log.info("getMainPage called with session id " + httpSession.getAttribute("sessionId"));

        if (httpSession.getAttribute("sessionId") != null) {
            String sessionId = httpSession.getAttribute("sessionId").toString();
            Session session = sessionRepo.findWithUser(UUID.fromString(sessionId)).orElseThrow(AccessForbiddenException::new);
            User user = session.getUser();
            Set<Location> userLocations = locationRepo.findByUserId(user.getId());

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
