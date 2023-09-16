package com.tonde.maisonchapback.services.impl;

import com.tonde.maisonchapback.domains.House;
import com.tonde.maisonchapback.domains.Status;
import com.tonde.maisonchapback.domains.User;
import com.tonde.maisonchapback.repositories.HouseRepository;
import com.tonde.maisonchapback.repositories.StatusRepository;
import com.tonde.maisonchapback.repositories.UserRepository;
import com.tonde.maisonchapback.services.interfaces.HouseService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Hidden
public class HouseServiceImpl implements HouseService {

    private final HouseRepository houseRepository;
    private final UserRepository userRepository;
    private final StatusRepository statusRepository;

    private final PhotoServiceImpl service;

    @Override
    public ResponseEntity<List<House>> getAllHouses() {
        return houseRepository.findAll().isEmpty() ? null : ResponseEntity.ok(houseRepository.findAll());
    }

    @Override
    public ResponseEntity<House> getHouseById(int id) {

        Optional<House> houseOptional = houseRepository.findById(id);
        return houseOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());

    }

    @Override
    public ResponseEntity<String> addHouse(House house) {
        houseRepository.save(house);
        return ResponseEntity.ok("Maison ajoutée avec succès");

    }

    @Override
    public ResponseEntity<String> updateHouse(House house) {
        Optional<House> houseOptional = houseRepository.findById(house.getId());
        if (houseOptional.isPresent()) {
            houseOptional.get().setAddress(house.getAddress());
            houseOptional.get().setCity(house.getCity());
            houseOptional.get().setCountry(house.getCountry());
            houseOptional.get().setNumberOfBathrooms(house.getNumberOfBathrooms());
            houseOptional.get().setTitle(house.getTitle());
            houseOptional.get().setSurface(house.getSurface());
            houseOptional.get().setStatusHouse(house.getStatusHouse());
            houseOptional.get().setTypeHouse(house.getTypeHouse());
            houseOptional.get().setNumberOfRooms(house.getNumberOfRooms());
            houseOptional.get().setPrice(house.getPrice());
            houseOptional.get().setDescription(house.getDescription());
            houseOptional.get().setNumberOfFloors(house.getNumberOfFloors());
            houseOptional.get().setUser(house.getUser());
            houseOptional.get().setCountry(house.getCountry());
            houseOptional.get().setDisponibility(house.getDisponibility());
            houseRepository.save(houseOptional.get());
            return ResponseEntity.ok("Maison modifiée avec succès");
        } else {
            return ResponseEntity.badRequest().body("Maison non trouvée");
        }

    }

    @Override
    public ResponseEntity<String> deleteHouse(int id) {
        Optional<House> houseOptional = houseRepository.findById(id);
        if (houseOptional.isPresent()) {
            houseRepository.delete(houseOptional.get());
            return ResponseEntity.ok("Maison supprimée avec succès");
        } else {
            return ResponseEntity.badRequest().body("Maison non trouvée");
        }

    }

    @Override
    public ResponseEntity<List<House>> getAllRentingHouses() {
        Optional<Status> status = statusRepository.findById(1);
        return ResponseEntity.ok(houseRepository.findAllHouseByStatusHouse(status.orElseThrow()));
    }

    @Override
    public ResponseEntity<List<House>> getAllSellingHouses() {
        Optional<Status> status;
        status = statusRepository.findById(2);
        return ResponseEntity.ok(houseRepository.findAllHouseByStatusHouse(status.orElseThrow()));
    }

    @Override
    public ResponseEntity<List<House>> getAllHousesByUserId(int userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.map(user -> ResponseEntity.ok(houseRepository.findAllByUser(user))).orElseGet(() -> ResponseEntity.badRequest().body(null));

    }

    @Override
    public ResponseEntity<List<House>> getAllHousesByStatus(int status) {
        Optional<Status> optional = statusRepository.findById(status);
        return optional.map(value -> ResponseEntity.ok(houseRepository.findAllHouseByStatusHouse(value))).orElseGet(() -> ResponseEntity.badRequest().body(null));
    }

    @Override
    public ResponseEntity<List<House>> getAllHousesByDisponibility(String disponibility) {
        List<House> houses = houseRepository.findAllByDisponibility(disponibility);
        if (houses.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(houses);
        }
    }
}
