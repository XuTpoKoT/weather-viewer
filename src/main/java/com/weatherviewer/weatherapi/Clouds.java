package com.weatherviewer.weatherapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Clouds {
    @JsonProperty("all")
    private Integer cloudiness;
}
