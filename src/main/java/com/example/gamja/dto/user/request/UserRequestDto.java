package com.example.gamja.dto.user.request;


import com.example.gamja.domain.user.User;
import lombok.Getter;

@Getter
public class UserRequestDto {
    private String username;
    private String email;
    private String password;

    public void applyTo(User user) {
        user.updateUsername(username);
        user.updateEmail(email);
        user.updatePassword(password);
    }

    // Dto to Entity
    public User toUser() {
        return User.builder()
                .username(username)
                .email(email)
                .password(password)
                .build();
    }
}
