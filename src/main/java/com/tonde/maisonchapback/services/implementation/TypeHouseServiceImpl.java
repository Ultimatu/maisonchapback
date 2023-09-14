package com.tonde.maisonchapback.services.implementation;

import com.tonde.maisonchapback.models.workflows.TypeHouse;
import com.tonde.maisonchapback.repositories.TypeHouseRepository;
import com.tonde.maisonchapback.services.interfaces.TypeHouseService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Hidden
public class TypeHouseServiceImpl implements TypeHouseService {

    private final TypeHouseRepository typeHouseRepository;

    @Override
    public ResponseEntity<List<TypeHouse>> getAllTypeHouse() {
        List<TypeHouse> typeHouseList = typeHouseRepository.findAll();
        if (typeHouseList.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(typeHouseList);
    }

    @Override
    public ResponseEntity<TypeHouse> getTypeHouseById(int id) {
        return typeHouseRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity
                                .badRequest()
                                .body(null));
    }

    @Override
    public ResponseEntity<?> createTypeHouse(TypeHouse typeHouse) {
        Optional<TypeHouse> typeHouseOptional = typeHouseRepository.findByTypeAndDescription(typeHouse.getType(), typeHouse.getDescription());
        if (typeHouseOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Type already exists");
        }
        typeHouseRepository.save(typeHouse);
        return ResponseEntity.ok("Type created");

    }

    @Override
    public ResponseEntity<?> updateTypeHouse(TypeHouse typeHouse) {
        Optional<TypeHouse> typeHouseOptional = typeHouseRepository.findById(typeHouse.getId());
        if (typeHouseOptional.isPresent()) {
            TypeHouse typeHouse1 = typeHouseOptional.get();
            typeHouse1.setType(typeHouse.getType());
            typeHouse1.setDescription(typeHouse.getDescription());
            typeHouse1.setUpdatedAt(LocalDateTime.now());
            typeHouseRepository.save(typeHouse1);
            return ResponseEntity.ok("Type updated");
        }
        return ResponseEntity.badRequest().body("Impossible to update this type");
    }

    @Override
    public ResponseEntity<?> deleteTypeHouse(int id) {
        Optional<TypeHouse> typeHouseOptional = typeHouseRepository.findById(id);
        if (typeHouseOptional.isPresent()) {
            typeHouseRepository.deleteById(id);
            return ResponseEntity.ok("Type deleted");
        }
        return ResponseEntity.badRequest().body("Impossible to delete this type");
    }
}
