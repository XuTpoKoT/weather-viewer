package com.weatherviewer.auth;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "Users", schema = "public")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @Getter
    private String login;
    @Getter
    private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
