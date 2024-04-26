package com.weather.auth;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Entity
@Table(name = "Sessions", schema = "public")
public class Session {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    public Session(User user, LocalDateTime expiresAt) {
        this.user = user;
        this.expiresAt = expiresAt;
    }
}
