package com.tonde.maisonchapback.controllers;

import com.tonde.maisonchapback.models.workflows.House;
import com.tonde.maisonchapback.services.implementation.HouseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/houses")
@CrossOrigin(origins = "http://localhost:4200")
public class HouseController {

    private final HouseServiceImpl houseService;

    @GetMapping("/all")
    public ResponseEntity<List<House>> allHouses() {
        return houseService.getAllHouses();
    }

    @GetMapping("/renting")

    public ResponseEntity<List<House>> allRentingHouses() {
        return houseService.getAllRentingHouses();
    }


    @GetMapping("/selling")
    public ResponseEntity<List<House>> allSellingHouses() {
        return houseService.getAllSellingHouses();
    }
}
