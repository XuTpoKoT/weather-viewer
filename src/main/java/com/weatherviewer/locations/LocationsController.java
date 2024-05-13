package com.weatherviewer.locations;

import com.weatherviewer.auth.AccessForbiddenException;
import com.weatherviewer.auth.Session;
import com.weatherviewer.auth.SessionRepo;
import com.weatherviewer.weatherapi.WeatherApiService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LocationsController {
    private final WeatherApiService weatherApiService;
    private final LocationRepo locationRepo;
    private final SessionRepo sessionRepo;

    @GetMapping("/locations")
    public String getLocationsByName(@RequestParam("q") String locationName, HttpSession httpSession, Model model) {
        log.info("getLocationsByName called, session " + httpSession.getAttribute("sessionId") + ", location "
                + locationName);

        List<LocationsResponse> locations = weatherApiService.getLocationsByName(locationName);
        model.addAttribute("locations", locations);
        return "search";
    }

    @PostMapping("/locations")
    public String addLocation(@ModelAttribute("addLocationRequest") AddLocationRequest req,
                              HttpSession httpSession) {
        String sessionId = httpSession.getAttribute("sessionId").toString();
        log.info("addLocation called, session id: " + sessionId + ", location: " + req.name());

        Session session = sessionRepo.findById(UUID.fromString(sessionId)).orElseThrow(AccessForbiddenException::new);
        Location location = new Location(req.name(), List.of(session.getUser()), req.latitude(), req.longitude());

        log.info("saving location to db...");
        locationRepo.save(location);

        return "redirect:/";
    }

    @DeleteMapping("/locations")
    public String deleteLocation(@RequestParam("locationId") UUID locationId,
                              HttpSession httpSession) {
        String sessionId = httpSession.getAttribute("sessionId").toString();
        log.info("deleteLocation called, session id: " + sessionId);

        locationRepo.deleteById(locationId);

        return "redirect:/";
    }
}
