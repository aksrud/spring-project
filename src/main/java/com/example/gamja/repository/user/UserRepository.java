package com.example.gamja.repository.user;
import com.example.gamja.domain.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);

    UserEntity findByUsername(String username);
}
