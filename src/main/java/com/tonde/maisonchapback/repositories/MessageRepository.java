package com.tonde.maisonchapback.repositories;

import com.tonde.maisonchapback.models.workflows.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tonde.maisonchapback.models.workflows.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findBySender(User user);

    List<Message> findByReceiver(User user);
}