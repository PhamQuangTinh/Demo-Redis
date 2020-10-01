package com.example.redis.repository;

import com.example.redis.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findById(Long id);

    @Query("select u from UserEntity u where u.username = ?1")
    Optional<UserEntity> findByUsernameRepository(String username);
}
