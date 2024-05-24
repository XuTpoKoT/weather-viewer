package com.weatherviewer.weatherapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.weatherviewer.weatherapi.model.Clouds;
import com.weatherviewer.weatherapi.model.Main;
import com.weatherviewer.weatherapi.model.Weather;
import com.weatherviewer.weatherapi.model.Wind;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherApiResponse {
        @JsonProperty("weather")
        private List<Weather> weatherList;
        @JsonProperty("main")
        private Main main;
        @JsonProperty("wind")
        private Wind wind;
        @JsonProperty("clouds")
        private Clouds clouds;
        @JsonProperty("dt")
        @JsonDeserialize(using = UnixTimestampDeserializer.class)
        private LocalDateTime dateTime;
        @JsonProperty("sys")
        private Sys sys;

        @Getter
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Sys {
                @JsonProperty("sunrise")
                @JsonDeserialize(using = UnixTimestampDeserializer.class)
                private LocalDateTime sunriseTime;

                @JsonProperty("sunset")
                @JsonDeserialize(using = UnixTimestampDeserializer.class)
                private LocalDateTime sunsetTime;
        }

        public String getIconUrl() {
                return "https://openweathermap.org/img/wn/" + weatherList.get(0).getIcon() + "@4x.png";
        }
}
