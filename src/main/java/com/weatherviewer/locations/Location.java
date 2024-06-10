package com.weatherviewer.locations;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Entity
@Table(name = "Locations"
        , schema = "public"
        , uniqueConstraints=@UniqueConstraint(columnNames={"latitude", "longitude"}))
@Getter
public class Location {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private Double latitude;
    private Double longitude;

    public Location(String name, Double latitude, Double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
