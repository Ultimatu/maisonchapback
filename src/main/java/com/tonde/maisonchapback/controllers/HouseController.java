package com.tonde.maisonchapback.controllers;

import com.tonde.maisonchapback.services.implementation.HouseServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/houses")
@CrossOrigin(origins = "http://localhost:4200")
public class HouseController {

    private final HouseServiceImpl houseService;





    @GetMapping("/all")
    public ResponseEntity<?> allHouses(){
        return houseService.getAllHouses();
    }

    @GetMapping("/renting")

    public ResponseEntity<?> allRentingHouses(){
        return houseService.getAllRentingHouses();
    }


    @GetMapping("/selling")
    public ResponseEntity<?> allSellingHouses(){
        return houseService.getAllSellingHouses();
    }
}
