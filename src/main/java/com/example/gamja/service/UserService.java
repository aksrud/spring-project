package com.example.gamja.service;
import com.example.gamja.dto.user.request.UserRequestDto;
import com.example.gamja.dto.user.response.UserResponseDto;

public interface UserService {
    // 사용자 생성
    void joinUser(UserRequestDto userRequestDto);

    // 사용자 조회 (ID로 찾기)
    UserResponseDto getUserById(Long id);

    // 사용자 수정
    UserResponseDto updateUser(Long id, UserRequestDto userRequestDto);

    // 사용자 삭제
    void deleteUser(Long id);
}