package com.tonde.maisonchapback.repositories;

import com.tonde.maisonchapback.models.workflows.Comments;
import com.tonde.maisonchapback.models.workflows.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comments, Integer> {
    Optional<Object> findAllByUser(User user);
}
