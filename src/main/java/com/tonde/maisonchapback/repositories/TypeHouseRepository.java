package com.tonde.maisonchapback.repositories;

import com.tonde.maisonchapback.domains.TypeHouse;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


@Hidden
public interface TypeHouseRepository extends JpaRepository<TypeHouse, Integer> {
    Optional<TypeHouse> findByTypeAndDescription(String type, String description);
}
