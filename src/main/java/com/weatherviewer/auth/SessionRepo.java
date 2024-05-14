package com.weatherviewer.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

public interface SessionRepo extends JpaRepository<Session, UUID> {
    @Transactional
    void deleteSessionsByExpiresAtLessThan(LocalDateTime dateTime);
}
