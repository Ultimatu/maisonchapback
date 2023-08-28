package com.tonde.maisonchapback.services.interfaces;

import com.tonde.maisonchapback.models.workflows.House;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Hidden
public interface HouseService {

    ResponseEntity<?> getAllHouses();

    ResponseEntity<?> getHouseById(int id);


    ResponseEntity<?> addHouse(House house);

    ResponseEntity<?> updateHouse(House house);

    ResponseEntity<?> deleteHouse(int house);

    ResponseEntity<?> getAllHousesByUserId(int userId);

    ResponseEntity<?> getAllHousesByStatus(int status);

    ResponseEntity<?> getAllHousesByDisponibility(String disponibility);

}
