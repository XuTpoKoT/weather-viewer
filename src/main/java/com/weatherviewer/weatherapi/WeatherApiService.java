package com.weatherviewer.weatherapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weatherviewer.locations.GetLocationsException;
import com.weatherviewer.locations.Location;
import com.weatherviewer.locations.LocationsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class WeatherApiService {
    @Value("${open-weather-key}")
    private String OPEN_WEATHER_KEY;
    private static final String BASE_API_URL = "https://api.openweathermap.org";
    private static final String LOCATIONS_API_URL_SUFFIX = "/geo/1.0/direct";
    private static final String WEATHER_API_URL_SUFFIX = "/data/2.5/weather";
    private static final String FORECAST_API_URL_SUFFIX = "/data/2.5/forecast";
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<LocationsResponse> getLocationsByName(String locationName) {
        try {
            URI uri = buildUriForGetLocationsRequest(locationName);
            HttpRequest request = buildRequest(uri);
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new GetLocationsException("Error with open weather service");
            }
            return objectMapper.readValue(response.body(), new TypeReference<>() {});
        } catch (IOException | InterruptedException e) {
            throw new GetLocationsException("Can not get locations for name = " + locationName, e);
        }
    }

    public WeatherApiResponse getWeatherForLocation(Location location) {
        try {
            URI uri = buildUriForWeatherRequest(location);
            HttpRequest request = buildRequest(uri);
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new GetWeatherException("Error with open weather service");
            }
            return objectMapper.readValue(response.body(), WeatherApiResponse.class);
        } catch (IOException | InterruptedException e) {
            throw new GetWeatherException("Can not get weather for location with id = " + location.getId());
        }
    }

    public ForecastApiResponse getForecastForLocation(Location location) {
        try {
            URI uri = buildUriForForecastRequest(location);
            HttpRequest request = buildRequest(uri);
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new GetForecastException("Error with open weather service");
            }
            return objectMapper.readValue(response.body(), ForecastApiResponse.class);
        } catch (IOException | InterruptedException e) {
            throw new GetForecastException("Issues with calling api for location with id = " + location.getId());
        }
    }

    private HttpRequest buildRequest(URI uri) {
        return HttpRequest.newBuilder(uri)
                .GET()
                .build();
    }
    private URI buildUriForGetLocationsRequest(String locationName) {
        return URI.create(BASE_API_URL + LOCATIONS_API_URL_SUFFIX
                + "?q=" + locationName
                + "&limit=5"
                + "&appid=" + OPEN_WEATHER_KEY);
    }

    private URI buildUriForWeatherRequest(Location location) {
        return URI.create(BASE_API_URL + WEATHER_API_URL_SUFFIX
                + "?lat=" + location.getLatitude()
                + "&lon=" + location.getLongitude()
                + "&appid=" + OPEN_WEATHER_KEY
                + "&units=" + "metric");
    }
    private URI buildUriForForecastRequest(Location location) {
        return URI.create(BASE_API_URL + FORECAST_API_URL_SUFFIX
                + "?lat=" + location.getLatitude()
                + "&lon=" + location.getLongitude()
                + "&appid=" + OPEN_WEATHER_KEY
                + "&units=" + "metric");
    }
}
