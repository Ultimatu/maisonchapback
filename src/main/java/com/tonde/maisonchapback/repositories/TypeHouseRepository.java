package com.tonde.maisonchapback.repositories;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tonde.maisonchapback.models.workflows.TypeHouse;

import java.util.Optional;


@Hidden
public interface TypeHouseRepository extends JpaRepository<TypeHouse, Integer> {
    Optional<TypeHouse> findByTypeAndDescription(String type, String description);
}
