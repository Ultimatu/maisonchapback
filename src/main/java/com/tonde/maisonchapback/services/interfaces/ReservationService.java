package com.tonde.maisonchapback.services.interfaces;

import com.tonde.maisonchapback.domains.Reservation;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Hidden
public interface ReservationService {

    ResponseEntity<List<Reservation>> getAllReservations();

    ResponseEntity<Reservation> getReservationById(int id);

    ResponseEntity<?> createReservation(Reservation reservation);

    ResponseEntity<?> updateReservation(Reservation reservation);

    ResponseEntity<?> deleteReservation(int id);

    ResponseEntity<?> getReservationByHouseId(int id);

    ResponseEntity<?> getReservationByUserId(int id);

    ResponseEntity<?> getReservationByHouseIdAndUserId(int houseId, int userId);


}
