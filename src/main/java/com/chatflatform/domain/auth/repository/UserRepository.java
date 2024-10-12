package com.chatflatform.domain.auth.repository;

import com.chatflatform.domain.auth.repository.entity.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByName(String name);
    boolean existsByName(String name);

    @Query("SELECT u.name FROM User AS u WHERE LOCATE(LOWER(:pattern), LOWER(u.name)) > 0 AND u.name != :user")
    List<String> findNameByNameMatch(@Param("pattern") String pattern, @Param("user") String user);
}
