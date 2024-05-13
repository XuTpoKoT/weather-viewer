package com.weatherviewer.locations;

public record AddLocationRequest (
        String name,
        Double latitude,
        Double longitude) {
}
