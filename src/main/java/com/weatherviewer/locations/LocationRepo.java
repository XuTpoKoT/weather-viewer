package com.weatherviewer.locations;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepo extends JpaRepository<Location, Long> {
    List<Location> findByUsersLogin(String login);
}
