package com.tonde.maisonchapback.repositories;

import com.tonde.maisonchapback.models.workflows.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status, Integer>{
    Optional<Status> findByStatusAndDescription(String status, String description);
}
