package com.weatherviewer.auth;

import com.password4j.Hash;
import com.password4j.Password;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    @Value("${session.duration-in-hours}")
    private long sessionDurationInHours;
    private final UserRepo userRepo;
    private final SessionRepo sessionRepo;
    @Transactional
    public UUID signUp(String login, String password) {
        try {
            Hash hash = Password.hash(password).withBcrypt();

            User user = new User(login, hash.getResult());
            userRepo.save(user);
            log.info("User " + login + " created");

            Session session = new Session(user, LocalDateTime.now().plusHours(sessionDurationInHours));
            sessionRepo.save(session);
            log.info("Session " + session.getId() + " created");

            return session.getId();
        } catch (DataIntegrityViolationException e) {
            throw new SignUpFailedException("Login " + login + " is occupied");
        }
    }

    public UUID signIn(String login, String password) {
        User user = userRepo.findByLogin(login).orElseThrow(BadCredentialsException::new);
        if (!Password.check(password, user.getPassword()).withBcrypt()) {
            throw new BadCredentialsException();
        }

        Session session = new Session(user, LocalDateTime.now().plusHours(sessionDurationInHours));
        sessionRepo.save(session);
        log.info("Session " + session.getId() + " created");

        return session.getId();
    }

    public void signOut(UUID sessionId) {
        sessionRepo.deleteById(sessionId);
    }
}
