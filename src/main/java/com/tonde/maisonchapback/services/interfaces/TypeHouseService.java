package com.tonde.maisonchapback.services.interfaces;

import com.tonde.maisonchapback.models.workflows.TypeHouse;
import com.tonde.maisonchapback.repositories.TypeHouseRepository;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Hidden
public interface TypeHouseService {

    public ResponseEntity<List<TypeHouse>> getAllTypeHouse();

    public ResponseEntity<TypeHouse> getTypeHouseById(int id);

    public ResponseEntity<?> createTypeHouse(TypeHouse typeHouse);

    public ResponseEntity<?> updateTypeHouse(TypeHouse typeHouse);

    public ResponseEntity<?> deleteTypeHouse(int id);


}
