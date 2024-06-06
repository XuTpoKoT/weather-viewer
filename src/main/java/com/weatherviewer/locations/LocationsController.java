package com.weatherviewer.locations;

import com.weatherviewer.auth.AccessForbiddenException;
import com.weatherviewer.auth.Session;
import com.weatherviewer.auth.SessionRepo;
import com.weatherviewer.auth.User;
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
    private final UserLocationRepo userLocationRepo;
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

        Location location = new Location(req.name(), req.latitude(), req.longitude());
        locationRepo.saveOrIgnore(location);

        Session session = sessionRepo.findWithUser(UUID.fromString(sessionId)).orElseThrow(AccessForbiddenException::new);
        User user = session.getUser();
        log.info("saving user_location to db...");
        userLocationRepo.save(new UserLocation(user.getId(), req.latitude(), req.longitude()));

        return "redirect:/";
    }

    @DeleteMapping("/locations")
    public String deleteLocation(@RequestParam("latitude") Double latitude, @RequestParam("longitude") Double longitude,
                                 HttpSession httpSession) {
        String sessionId = httpSession.getAttribute("sessionId").toString();
        log.info("deleteLocation called, session id: " + sessionId);

        Session session = sessionRepo.findWithUser(UUID.fromString(sessionId)).orElseThrow(AccessForbiddenException::new);
        User user = session.getUser();
        userLocationRepo.deleteByUserIdAndLatitudeAndLongitude(user.getId(), latitude, longitude);

        return "redirect:/";
    }
}
