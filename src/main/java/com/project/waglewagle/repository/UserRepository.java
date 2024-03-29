package com.project.waglewagle.repository;

import com.project.waglewagle.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByEmail(String email);

    Optional<Users> findByBroad_id(Long broadId);
}
