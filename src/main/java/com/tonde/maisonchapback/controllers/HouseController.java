package com.tonde.maisonchapback.controllers;

import com.tonde.maisonchapback.domains.House;
import com.tonde.maisonchapback.services.impl.HouseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/public/houses")
@CrossOrigin(origins = "http://localhost:4200")
public class HouseController {

    private final HouseServiceImpl houseService;


    /**
     * {@code GET  /api/public/houses/all} : Recuperer toutes les maisons
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all houses.
     * @return the {@link ResponseEntity} with status {@code 404 (Not Found)} if no houses found.
     * @return the {@link ResponseEntity} with status {@code 500 (Internal Server Error)} if an error occurred.
     */
    @GetMapping("/all")
    public ResponseEntity<List<House>> allHouses() {
        return houseService.getAllHouses();
    }


    /**
     * {@code GET  /api/public/houses/renting} : Recuperer toutes les maisons en location
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all renting houses.
     * @return the {@link ResponseEntity} with status {@code 404 (Not Found)} if no renting houses found.
     * @return
     */
    @GetMapping("/renting")

    public ResponseEntity<List<House>> allRentingHouses() {
        return houseService.getAllRentingHouses();
    }


    /**
     * {@code GET  /api/public/houses/selling} : Recuperer toutes les maisons en vente
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all selling houses.
     * @return the {@link ResponseEntity} with status {@code 404 (Not Found)} if no selling houses found.
     * @return
     */
    @GetMapping("/selling")
    public ResponseEntity<List<House>> allSellingHouses() {
        return houseService.getAllSellingHouses();
    }
}
