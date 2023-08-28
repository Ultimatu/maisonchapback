package com.tonde.maisonchapback.repositories;

import com.tonde.maisonchapback.models.workflows.user.User;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Hidden
public interface UserRepository extends JpaRepository<User, Integer> {


    Optional<User> findByEmail(String username);

    boolean existsByEmailOrPhone(String email, String telephone);
}
