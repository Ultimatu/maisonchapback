package com.tonde.maisonchapback.repositories;

import com.tonde.maisonchapback.models.workflows.Statistique;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatRepository extends JpaRepository<Statistique, Integer> {
}
