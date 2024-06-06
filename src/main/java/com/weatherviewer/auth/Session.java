package com.weatherviewer.auth;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Sessions", schema = "public")
@Getter
@NoArgsConstructor
public class Session {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    public Session(User user, LocalDateTime expiresAt) {
        this.user = user;
        this.expiresAt = expiresAt;
    }
}
