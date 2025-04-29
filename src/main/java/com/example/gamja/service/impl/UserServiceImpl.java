package com.example.gamja.service.impl;

import com.example.gamja.domain.user.User;
import com.example.gamja.dto.user.request.UserRequestDto;
import com.example.gamja.dto.user.response.UserResponseDto;
import com.example.gamja.repository.user.UserRepository;
import com.example.gamja.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 사용자 생성
    @Transactional
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        if(userRepository.existsByEmail(userRequestDto.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        } else if (userRepository.existsByUsername(userRequestDto.getUsername())) {
            throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");
        }
        User user = userRequestDto.toUser();
        userRepository.save(user); // 처음 만들어진 엔티티는 영속성 컨텍스트에 속하지 않기 때문에 따로 save를 해야됨
        return UserResponseDto.from(user);
    }

    // 사용자 조회 (ID로 찾기)
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return UserResponseDto.from(user);
    }

    // 사용자 수정
    @Transactional
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRequestDto.applyTo(user);
        return UserResponseDto.from(user);
    }

    // 사용자 삭제
    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }
}
