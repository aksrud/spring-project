package com.example.gamja.controller;
import com.example.gamja.dto.user.request.UserRequestDto;
import com.example.gamja.dto.user.response.UserResponseDto;
import com.example.gamja.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 사용자 회윈 가입(생성) (POST)
    @PostMapping("/join")
    public ResponseEntity<Void> joinUser(@RequestBody UserRequestDto userRequestDto) {
        userService.joinUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 사용자 조회 (GET by ID)
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        UserResponseDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    // 사용자 수정 (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto) {
        UserResponseDto updatedUser = userService.updateUser(id, userRequestDto);
        return ResponseEntity.ok(updatedUser);
    }

    // 사용자 삭제 (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
