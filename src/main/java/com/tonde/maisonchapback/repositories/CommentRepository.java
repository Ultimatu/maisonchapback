package com.tonde.maisonchapback.repositories;

import com.tonde.maisonchapback.domains.Comments;
import com.tonde.maisonchapback.domains.User;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Hidden
public interface CommentRepository extends JpaRepository<Comments, Integer> {
    List<Comments> findAllByUser(User user);
}
