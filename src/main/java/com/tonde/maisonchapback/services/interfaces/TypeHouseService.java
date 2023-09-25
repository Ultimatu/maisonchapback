package com.tonde.maisonchapback.services.interfaces;

import com.tonde.maisonchapback.domains.TypeHouse;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Hidden
public interface TypeHouseService {

    List<TypeHouse> getAllTypeHouse();

    Optional<TypeHouse> getTypeHouseById(int id);

    ResponseEntity<TypeHouse> createTypeHouse(TypeHouse typeHouse);

    ResponseEntity<TypeHouse> updateTypeHouse(TypeHouse typeHouse);

    void deleteTypeHouse(int id);


}
