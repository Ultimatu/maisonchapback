package com.tonde.maisonchapback.repositories;

import com.tonde.maisonchapback.models.workflows.Status;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Hidden
public interface StatusRepository extends JpaRepository<Status, Integer>{
    Optional<Status> findByStatusAndDescription(String status, String description);
}
