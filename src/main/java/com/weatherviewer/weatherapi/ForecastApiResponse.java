package com.weatherviewer.weatherapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.weatherviewer.weatherapi.model.Main;
import com.weatherviewer.weatherapi.model.Weather;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ForecastApiResponse {
    @JsonProperty("list")
    private List<ForecastForHour> forecasts;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ForecastForHour {
        @JsonProperty("dt")
        @JsonDeserialize(using = UnixTimestampDeserializer.class)
        private LocalDateTime dateTime;
        @JsonProperty("main")
        private Main main;
        @JsonProperty("weather")
        private List<Weather> weatherList;

        public String getIconUrl() {
            return "https://openweathermap.org/img/wn/" + weatherList.get(0).getIcon() + "@4x.png";
        }
    }
}
