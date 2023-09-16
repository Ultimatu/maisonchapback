package com.tonde.maisonchapback.repositories;

import com.tonde.maisonchapback.domains.Statistique;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;

@Hidden
public interface StatRepository extends JpaRepository<Statistique, Integer> {
}
