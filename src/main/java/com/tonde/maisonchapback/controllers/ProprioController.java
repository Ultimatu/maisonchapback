package com.tonde.maisonchapback.controllers;


import com.tonde.maisonchapback.models.workflows.House;
import com.tonde.maisonchapback.models.workflows.Photo;
import com.tonde.maisonchapback.models.workflows.Reservation;
import com.tonde.maisonchapback.services.implementation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/proprio")
@PreAuthorize("hasRole('ROLE_FREE_PROPRIO') or hasRole('ROLE_PREMIUM_PROPRIO') or hasRole('ROLE_STANDARD_PROPRIO')")
@CrossOrigin(origins = "http://localhost:4200")
public class ProprioController {

    private final UserServiceImpl userService;

    private final HouseServiceImpl houseService;

    private final PhotoServiceImpl photoService;

    private final ReservationServiceImpl reservationService;


    /*
     *                      House APIs Section
     */

    @PostMapping("/addHouse")
    public ResponseEntity<?> addHouse(House house) {
        return houseService.addHouse(house);

    }

    @PostMapping("/updateHouse")
    public ResponseEntity<?> updateHouse(House house) {
        return houseService.updateHouse(house);
    }

    @PostMapping("/deleteHouse")
    public ResponseEntity<?> deleteHouse(int id) {
        return houseService.deleteHouse(id);
    }

    @PostMapping("/getAllHousesByUserId")
    public ResponseEntity<?> getAllHousesByUserId(int userId) {
        return houseService.getAllHousesByUserId(userId);
    }

    @PostMapping("/getAllHousesByStatus")
    public ResponseEntity<?> getAllHousesByStatus(int status) {
        return houseService.getAllHousesByStatus(status);
    }

    @PostMapping("/getAllHousesByDisponibility")

    public ResponseEntity<?> getAllHousesByDisponibility(String disponibility) {
        return houseService.getAllHousesByDisponibility(disponibility);
    }



    /*
     *                      Photo APIs Section
     */

    @PostMapping("/addPhoto")
    public ResponseEntity<?> addPhotos(List<MultipartFile>  files, Photo photo) throws IOException {
        return photoService.addPhotos(files, photo);
    }

    @PostMapping("/deletePhoto")
    public ResponseEntity<?> deletePhoto(int id) {
        return photoService.deletePhoto(id);
    }

    @PostMapping("/getAllPhotosByHouseId")
    public ResponseEntity<?> getAllPhotosByHouseId(int houseId) {
        return (ResponseEntity<?>) photoService.getAllPhotosByHouseId(houseId);
    }

    /*
     *                      Reservation APIs Section
     */


    @PostMapping("/addReservation")
    public ResponseEntity<?> addReservation(@RequestBody Reservation reservation) {
        return reservationService.createReservation(reservation);

    }

    @PostMapping("/updateReservation")
    public ResponseEntity<?> updateReservation(@RequestBody Reservation reservation) {
        return reservationService.updateReservation(reservation);
    }




}