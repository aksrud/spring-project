package com.example.gamja.service.impl;

import com.example.gamja.domain.user.UserEntity;
import com.example.gamja.dto.user.request.UserRequestDto;
import com.example.gamja.dto.user.response.UserResponseDto;
import com.example.gamja.repository.user.UserRepository;
import com.example.gamja.service.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    // 사용자 생성
//    @Transactional
//    public UserResponseDto createUser(UserRequestDto userRequestDto) {
//        if(userRepository.existsByEmail(userRequestDto.getEmail())) {
//            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
//        } else if (userRepository.existsByUsername(userRequestDto.getUsername())) {
//            throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");
//        }
//        UserEntity userEntity = userRequestDto.toUser();
//        userEntity.updatePassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
//        userRepository.save(userEntity); // 처음 만들어진 엔티티는 영속성 컨텍스트에 속하지 않기 때문에 따로 save를 해야됨
//
//        return UserResponseDto.from(userEntity);
//    }

    // 사용자 생성
    @Transactional
    public void joinUser(UserRequestDto userRequestDto) {
        if (userRepository.existsByUsername(userRequestDto.getUsername())) {
            throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");
        }
        else if(userRepository.existsByEmail(userRequestDto.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }
        String password = bCryptPasswordEncoder.encode(userRequestDto.getPassword());
        UserEntity userentity = userRequestDto.toUser();
        userentity.updatePassword(password);
        userRepository.save(userentity);
    }

    // 사용자 조회 (ID로 찾기)
    public UserResponseDto getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return UserResponseDto.from(userEntity);
    }

    // 사용자 수정
    @Transactional
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRequestDto.applyTo(userEntity);
        return UserResponseDto.from(userEntity);
    }

    // 사용자 삭제
    @Transactional
    public void deleteUser(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(userEntity);
    }
}
