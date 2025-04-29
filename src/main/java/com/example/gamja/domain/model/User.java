package com.example.gamja.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public static User create(String username, String email, String password) {
        return new User(username, email, password);
    }

    public void updateUser(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
