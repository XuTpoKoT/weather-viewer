package com.weatherviewer.locations;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Entity
@Table(name = "Locations", schema = "public")
public class Location {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private UUID id;
    @Getter
    private String name;
    @Getter
    private Double latitude;
    @Getter
    private Double longitude;

    public Location(String name, Double latitude, Double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
