package com.weatherviewer.locations;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Entity
@Table(name = "User_Location"
        , schema = "public"
        , uniqueConstraints=@UniqueConstraint(columnNames={"user_id", "latitude", "longitude"}))
public class UserLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "user_id")
    private Long userId;
    private Double latitude;
    private Double longitude;

    public UserLocation(Long userId, Double latitude, Double longitude) {
        this.userId = userId;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
