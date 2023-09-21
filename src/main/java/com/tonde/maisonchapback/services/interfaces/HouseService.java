package com.tonde.maisonchapback.services.interfaces;

import com.tonde.maisonchapback.domains.House;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Hidden
public interface HouseService {

    List<House> getAllHouses();

    House getHouseById(int id);


    ResponseEntity<String> addHouse(House house);

    ResponseEntity<String> updateHouse(House house);

    ResponseEntity<String> deleteHouse(int house);

    List<House> getAllRentingHouses();

    List<House> getAllSellingHouses();


    List<House> getAllHousesByUserId(int userId);

    List<House> getAllHousesByStatus(int status);

    List<House> getAllHousesByDisponibility(String disponibility);

}
