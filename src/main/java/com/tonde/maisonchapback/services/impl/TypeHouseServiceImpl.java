package com.tonde.maisonchapback.services.impl;

import com.tonde.maisonchapback.domains.TypeHouse;
import com.tonde.maisonchapback.repositories.TypeHouseRepository;
import com.tonde.maisonchapback.services.interfaces.TypeHouseService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Hidden
public class TypeHouseServiceImpl implements TypeHouseService {

    private final TypeHouseRepository typeHouseRepository;

    @Override
    public List<TypeHouse> getAllTypeHouse() {
        List<TypeHouse> typeHouseList = typeHouseRepository.findAll();
        if (typeHouseList.isEmpty()) {
            return Collections.emptyList();
        }
        return typeHouseList;
    }

    @Override
    public Optional<TypeHouse> getTypeHouseById(int id) {
        return typeHouseRepository.findById(id);
    }

    @Override
    public ResponseEntity<TypeHouse> createTypeHouse(TypeHouse typeHouse) {
        Optional<TypeHouse> typeHouseOptional = typeHouseRepository.findByTypeAndDescription(typeHouse.getType(), typeHouse.getDescription());
        if (typeHouseOptional.isPresent()) {
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.ok(typeHouseRepository.save(typeHouse));

    }

    @Override
    public ResponseEntity<TypeHouse> updateTypeHouse(TypeHouse typeHouse) {
        Optional<TypeHouse> typeHouseOptional = typeHouseRepository.findById(typeHouse.getId());
        if (typeHouseOptional.isPresent()) {
            TypeHouse typeHouse1 = typeHouseOptional.get();
            typeHouse1.setType(typeHouse.getType());
            typeHouse1.setDescription(typeHouse.getDescription());
            typeHouse1.setDateModification(Instant.from(LocalDateTime.now()));

            return ResponseEntity.ok(typeHouseRepository.save(typeHouse1));
        }
        return ResponseEntity.badRequest().body(null);
    }

    @Override
    public void deleteTypeHouse(int id) {
        Optional<TypeHouse> typeHouseOptional = typeHouseRepository.findById(id);
        if (typeHouseOptional.isPresent()) {
            typeHouseRepository.deleteById(id);

        }
    }
}
