package com.tonde.maisonchapback.repositories;

import com.tonde.maisonchapback.domains.User;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.Optional;

@Hidden
public interface UserRepository extends JpaRepository<User, Serializable> {


    Optional<User> findByEmail(String username);

    boolean existsByEmailOrPhone(String email, String telephone);

    boolean existsByEmail(String email);
}
