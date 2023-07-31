package com.tonde.maisonchapback.repositories;

import com.tonde.maisonchapback.models.workflows.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
