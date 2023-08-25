package com.tonde.maisonchapback.services.interfaces;

import com.tonde.maisonchapback.models.workflows.House;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Hidden
public interface HouseService {

    public ResponseEntity<?> getAllHouses();

    public ResponseEntity<?> getHouseById(int id);


    public ResponseEntity<?> addHouse(House house);

    public ResponseEntity<?> updateHouse(House house);

    public ResponseEntity<?> deleteHouse(int house);

    public ResponseEntity<?> getAllHousesByUserId(int userId);

    public ResponseEntity<?> getAllHousesByStatus(int status);

    public ResponseEntity<?> getAllHousesByDisponibility(String disponibility);

}
