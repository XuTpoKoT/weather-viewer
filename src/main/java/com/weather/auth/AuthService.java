package com.weather.auth;

import com.password4j.Hash;
import com.password4j.Password;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Log
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepo userRepo;
    private final SessionRepo sessionRepo;
    @Transactional
    public UUID signUp(String login, String password) {
        try {
            Hash hash = Password.hash(password).withBcrypt();

            User user = new User(login, hash.getResult());
            userRepo.save(user);
            log.info("User " + login + " created");

            Session session = new Session(user, LocalDateTime.now().plusHours(1));
            sessionRepo.save(session);
            log.info("Session " + session.getId() + " created");

            return session.getId();
        } catch (RuntimeException e) {
            throw new SignUpFailedException();
        }

    }
}
