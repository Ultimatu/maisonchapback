package com.tonde.maisonchapback.repositories;

import com.tonde.maisonchapback.models.workflows.Message;
import com.tonde.maisonchapback.models.workflows.user.User;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Hidden
public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findBySender(User user);

    List<Message> findByReceiver(User user);
}
