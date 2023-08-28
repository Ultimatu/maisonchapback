package com.tonde.maisonchapback.services.implementation;

import com.tonde.maisonchapback.models.workflows.House;
import com.tonde.maisonchapback.models.workflows.Status;
import com.tonde.maisonchapback.models.workflows.TypeHouse;
import com.tonde.maisonchapback.repositories.HouseRepository;
import com.tonde.maisonchapback.repositories.StatusRepository;
import com.tonde.maisonchapback.repositories.TypeHouseRepository;
import com.tonde.maisonchapback.services.interfaces.SearchService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Hidden
public class SearchServiceImpl implements SearchService {

    private final HouseRepository houseRepository;
    private final StatusRepository statusRepository;
    private final TypeHouseRepository typeHouseRepository;
    @Override
    public ResponseEntity<?> searchHouseByCity(String city) {
        return houseRepository.findAllByCity(city).isEmpty() ? null : ResponseEntity.ok(houseRepository.findAllByCity(city));
    }

    @Override
    public ResponseEntity<?> searchHouseByPrice(String price) {
        return houseRepository.findAllByPrice(price).isEmpty() ? null : ResponseEntity.ok(houseRepository.findAllByPrice(price));
    }

    @Override
    public ResponseEntity<?> searchHouseByStatus(int status) {
        Optional<Status> statusOptional = statusRepository.findById(status);
        if(statusOptional.isPresent()){
            return ResponseEntity.ok(houseRepository.findAllHouseByStatusHouse(statusOptional.get()));
        }
        else{
            return ResponseEntity.badRequest().body("Status non trouvé");
        }
    }

    @Override
    public ResponseEntity<?> searchHouseByTypeAndCityAndPrice(int type, String city, String price) {
        Optional< TypeHouse> typeHouseOptional = typeHouseRepository.findById(type);
        if(typeHouseOptional.isPresent()){
            return ResponseEntity.ok(houseRepository.findAllByTypeHouseAndCityAndPrice(typeHouseOptional.get(), city, price));
        }
        else{
            return ResponseEntity.badRequest().body("Type non trouvé");
        }
    }

    @Override
    public ResponseEntity<?> searchHouseByAnyWord(String word) {
        return houseRepository.searchByAnyWord(word).isEmpty() ? null : ResponseEntity.ok(houseRepository.searchByAnyWord(word));
    }

    @Override
    public ResponseEntity<?> searchHouseByType(int type) {
        Optional< TypeHouse> typeHouseOptional = typeHouseRepository.findById(type);
        if(typeHouseOptional.isPresent()){
            return ResponseEntity.ok(houseRepository.findAllByTypeHouse(typeHouseOptional.get()));
        }
        else{
            return ResponseEntity.badRequest().body("Type non trouvé");
        }
    }

    @Override
    public ResponseEntity<?> searchHouseByCityAndPrice(String city, String price) {
        List<House> houses = houseRepository.findAllByCityAndPrice(city, price);
        return houses.isEmpty() ? null : ResponseEntity.ok(houses);
    }

    @Override
    public ResponseEntity<?> searchHouseByTypeAndPrice(int type, String price) {
        return (ResponseEntity<?>) houseRepository.getByTypeAndPrice(type, price);
    }

    @Override
    public ResponseEntity<?> searchByAll(int type, String city, String price, String surface, int status, String rooms) {
        return houseRepository.searchByAll(type, city, price, surface, status, rooms).isEmpty() ? null : ResponseEntity.ok(houseRepository.searchByAll(type, city, price, surface, status, rooms));
    }

    @Override
    public ResponseEntity<?> searchByTitleOrDescription(String word) {
        return houseRepository.searchByTitleOrDescription(word).isEmpty() ? null : ResponseEntity.ok(houseRepository.searchByTitleOrDescription(word));
    }

    @Override
    public ResponseEntity<?> searchByTitleAndDescription(String word1, String word2) {

        return houseRepository.searchByTitleAndDescription(word1, word2).isEmpty() ? null : ResponseEntity.ok(houseRepository.searchByTitleAndDescription(word1, word2));
    }


}
