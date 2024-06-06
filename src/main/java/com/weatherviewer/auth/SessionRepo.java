package com.weatherviewer.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface SessionRepo extends JpaRepository<Session, UUID> {
    @Query("SELECT s FROM Session s JOIN FETCH s.user WHERE s.id = :sessionId")
    Optional<Session> findWithUser(@Param("sessionId") UUID sessionId);
    @Transactional
    void deleteSessionsByExpiresAtLessThan(LocalDateTime dateTime);
}
