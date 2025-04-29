package com.example.gamja.service;

import com.example.gamja.domain.model.User;
import com.example.gamja.dto.user.request.UserRequestDto;
import com.example.gamja.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 사용자 생성
    public User createUser(UserRequestDto userRequestDto) {
        User user = User.create(
                userRequestDto.getUsername(),
                userRequestDto.getEmail(),
                userRequestDto.getPassword()
        );
        return userRepository.save(user);
    }

    // 사용자 조회 (ID로 찾기)
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    // 사용자 수정
    public User updateUser(Long id, UserRequestDto userRequestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRequestDto.applyTo(user);
        return userRepository.save(user);
    }

    // 사용자 삭제
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }
}
