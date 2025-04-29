package com.example.gamja.dto.user.request;


import com.example.gamja.domain.model.User;
import lombok.Getter;

@Getter
public class UserRequestDto {
    private String username;
    private String email;
    private String password;

    public void applyTo(User user) {
        user.updateUser(username, email, password);
    }
}
