package com.chatflatform.domain.auth.repository;

import com.chatflatform.domain.auth.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByName(String name);

    boolean existsByName(String name);
}
