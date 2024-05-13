package com.weatherviewer.locations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LocationsResponse(
        @JsonProperty("name")
        String name,
        @JsonProperty("lat")
        Double latitude,
        @JsonProperty("lon")
        Double longitude,
        @JsonProperty("country")
        String country) {
}
