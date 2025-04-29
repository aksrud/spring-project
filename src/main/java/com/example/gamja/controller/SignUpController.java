package com.example.gamja.controller;

import com.example.gamja.domain.model.User;
import com.example.gamja.dto.user.request.UserRequestDto;
import com.example.gamja.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class SignUpController  {
    private final UserService userService;

    @Autowired
    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    // 사용자 생성 (POST)
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserRequestDto userRequestDto) {
        User createdUser = userService.createUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    // 사용자 조회 (GET by ID)
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    // 사용자 수정 (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto) {
        User updatedUser = userService.updateUser(id, userRequestDto);
        return ResponseEntity.ok(updatedUser);
    }

    // 사용자 삭제 (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
