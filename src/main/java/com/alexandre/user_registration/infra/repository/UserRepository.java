package com.alexandre.user_registration.infra.repository;

import com.alexandre.user_registration.infra.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String UserEmail);

    @Transactional
    void deleteByEmail(String userEmail);
}
