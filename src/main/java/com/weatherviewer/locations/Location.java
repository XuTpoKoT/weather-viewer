package com.weatherviewer.locations;

import com.weatherviewer.auth.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
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
    @ManyToMany
    @JoinTable(name = "User_Location",
            joinColumns = @JoinColumn(name = "location_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;
    @Getter
    private Double latitude;
    @Getter
    private Double longitude;

    public Location(String name, List<User> users, Double latitude, Double longitude) {
        this.name = name;
        this.users = users;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
