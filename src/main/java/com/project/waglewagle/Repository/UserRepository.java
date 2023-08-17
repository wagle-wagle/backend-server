package com.project.waglewagle.Repository;

import com.project.waglewagle.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Long> {
    Users findByEmail(String email);
}
