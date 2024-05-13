package com.weatherviewer.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SessionRepo extends JpaRepository<Session, UUID> {
}
