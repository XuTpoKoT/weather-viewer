package com.weatherviewer.locations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

public interface LocationRepo extends JpaRepository<Location, UUID> {
    @Query("select l from Location l" +
            " join UserLocation ul on ul.latitude = l.latitude and ul.longitude = l.longitude and ul.userId = :userId")
    Set<Location> findByUserId(@Param("userId") Long userId);
    @Query(nativeQuery = true,
        value = "insert into locations (name, longitude, latitude) values " +
                "(:#{#location.name}, :#{#location.longitude}, :#{#location.latitude}) " +
                "on conflict(longitude, latitude) do nothing")
    @Modifying
    @Transactional
    void saveOrIgnore(@Param("location") Location location);
}
