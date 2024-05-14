package com.weatherviewer.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class SessionDeletingService {
    private final SessionRepo sessionRepo;

    public SessionDeletingService(SessionRepo sessionRepo) {
        this.sessionRepo = sessionRepo;
    }

    @Scheduled(timeUnit = TimeUnit.MINUTES, fixedRate = 2)
    public void myTask() {
        log.info("Deleting expired sessions");
        sessionRepo.deleteSessionsByExpiresAtLessThan(LocalDateTime.now());
    }
}
