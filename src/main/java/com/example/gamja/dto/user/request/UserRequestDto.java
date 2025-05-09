package com.example.gamja.dto.user.request;


import com.example.gamja.domain.user.UserEntity;
import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class UserRequestDto {

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.")
    private String username;

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;

    public void applyTo(UserEntity userEntity) {
        userEntity.updateUsername(username);
        userEntity.updateEmail(email);
        userEntity.updatePassword(password);
    }

    // Dto to Entity
    public UserEntity toUser() {
        return UserEntity.builder()
                .username(username)
                .email(email)
                .password(password)
                .role("ROLE_USER")
                .build();
    }
}
