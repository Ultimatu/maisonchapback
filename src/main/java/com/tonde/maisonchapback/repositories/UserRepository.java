package com.tonde.maisonchapback.repositories;

import com.tonde.maisonchapback.models.workflows.user.User;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {


    Optional<User> findByEmail(String username);

    boolean existsByEmailOrPhone(String email, String telephone);
}
