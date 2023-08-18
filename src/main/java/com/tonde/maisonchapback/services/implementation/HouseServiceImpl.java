package com.tonde.maisonchapback.services.implementation;

import com.tonde.maisonchapback.models.workflows.House;
import com.tonde.maisonchapback.models.workflows.Status;
import com.tonde.maisonchapback.models.workflows.user.User;
import com.tonde.maisonchapback.repositories.HouseRepository;
import com.tonde.maisonchapback.repositories.StatusRepository;
import com.tonde.maisonchapback.repositories.UserRepository;
import com.tonde.maisonchapback.services.interfaces.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
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
    public ResponseEntity<?> getHouseById(int id) {

        Optional<House> houseOptional = houseRepository.findById(id);
        if(houseOptional.isPresent()){
            return ResponseEntity.ok(houseOptional.get());
        }
        else{
            return ResponseEntity.badRequest().body("Maison non trouvée");
        }

    }

    @Override
    public ResponseEntity<?> addHouse(House house) {
        houseRepository.save(house);
        return ResponseEntity.ok("Maison ajoutée avec succès");

    }

    @Override
    public ResponseEntity<?> updateHouse(House house) {
        Optional<House> houseOptional = houseRepository.findById(house.getId());
        if(houseOptional.isPresent()){
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
        }
        else{
            return ResponseEntity.badRequest().body("Maison non trouvée");
        }

    }

    @Override
    public ResponseEntity<?> deleteHouse(int id) {
        Optional<House> houseOptional = houseRepository.findById(id);
        if(houseOptional.isPresent()){
            houseRepository.delete(houseOptional.get());
            return ResponseEntity.ok("Maison supprimée avec succès");
        }
        else{
            return ResponseEntity.badRequest().body("Maison non trouvée");
        }

    }

    @Override
    public ResponseEntity<?> getAllHousesByUserId(int userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            return ResponseEntity.ok(houseRepository.findAllByUser(userOptional.get()));
        }
        else{
            return ResponseEntity.badRequest().body("Utilisateur non trouvé");
        }

    }

    @Override
    public ResponseEntity<?> getAllHousesByStatus(int status) {
        Optional<Status> optional = statusRepository.findById(status);
        if(optional.isPresent()){
            return ResponseEntity.ok(houseRepository.findAllHouseByStatusHouse(optional.get()));
        }
        else{
            return ResponseEntity.badRequest().body("Status non trouvé");
        }
    }

    @Override
    public ResponseEntity<?> getAllHousesByDisponibility(String disponibility) {
        List<House> houses = houseRepository.findAllByDisponibility(disponibility);
        if(houses.isEmpty()){
            return ResponseEntity.badRequest().body("Aucune maison disponible");
        }
        else{
            return ResponseEntity.ok(houses);
        }
    }
}
