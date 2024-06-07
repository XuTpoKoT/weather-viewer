package com.weatherviewer.locations;


public class LocationNameValidator {

    public static boolean isValid(String locationName) {
        return locationName != null && !locationName.isBlank()
                && !locationName.matches(".*[0-9_,;!.><?&@#$%^*()/\\s].*") && locationName.length() < 64;
    }
}