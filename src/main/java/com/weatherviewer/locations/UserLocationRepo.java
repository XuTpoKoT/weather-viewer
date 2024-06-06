package com.weatherviewer.locations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface UserLocationRepo extends JpaRepository<UserLocation, UUID> {
    @Transactional
    void deleteByUserIdAndLatitudeAndLongitude(Long userId, Double latitude, Double longitude);
}
