package com.example.juridov.my_app_run.repository;

import com.example.juridov.my_app_run.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String name);
}
