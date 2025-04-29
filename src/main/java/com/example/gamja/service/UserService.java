package com.example.gamja.service;
import com.example.gamja.dto.user.request.UserRequestDto;
import com.example.gamja.dto.user.response.UserResponseDto;

public interface UserService {
    // 사용자 생성
    public UserResponseDto createUser(UserRequestDto userRequestDto);

    // 사용자 조회 (ID로 찾기)
    public UserResponseDto getUserById(Long id);

    // 사용자 수정
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto);

    // 사용자 삭제
    public void deleteUser(Long id);
}