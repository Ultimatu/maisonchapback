package com.tonde.maisonchapback.services.interfaces;

import com.tonde.maisonchapback.models.workflows.TypeHouse;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Hidden
public interface TypeHouseService {

    ResponseEntity<List<TypeHouse>> getAllTypeHouse();

    ResponseEntity<TypeHouse> getTypeHouseById(int id);

    ResponseEntity<?> createTypeHouse(TypeHouse typeHouse);

    ResponseEntity<?> updateTypeHouse(TypeHouse typeHouse);

    ResponseEntity<?> deleteTypeHouse(int id);


}
