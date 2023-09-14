package com.tonde.maisonchapback.services.interfaces;

import com.tonde.maisonchapback.models.workflows.House;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Hidden
public interface HouseService {

    ResponseEntity<List<House>> getAllHouses();

    ResponseEntity<House> getHouseById(int id);


    ResponseEntity<String> addHouse(House house);

    ResponseEntity<String> updateHouse(House house);

    ResponseEntity<String> deleteHouse(int house);

    ResponseEntity<List<House>> getAllRentingHouses();

    ResponseEntity<List<House>> getAllSellingHouses();


    ResponseEntity<List<House>> getAllHousesByUserId(int userId);

    ResponseEntity<List<House>> getAllHousesByStatus(int status);

    ResponseEntity<List<House>> getAllHousesByDisponibility(String disponibility);

}
