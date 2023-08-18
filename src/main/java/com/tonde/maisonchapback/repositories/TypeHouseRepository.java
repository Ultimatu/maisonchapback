package com.tonde.maisonchapback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tonde.maisonchapback.models.workflows.TypeHouse;

import java.util.Optional;


public interface TypeHouseRepository extends JpaRepository<TypeHouse, Integer> {
    Optional<TypeHouse> findByTypeAndDescription(String type, String description);
}
