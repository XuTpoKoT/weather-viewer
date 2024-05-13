package com.weatherviewer.locations;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LocationRepo extends JpaRepository<Location, UUID> {
    List<Location> findByUsersLogin(String login);
}
