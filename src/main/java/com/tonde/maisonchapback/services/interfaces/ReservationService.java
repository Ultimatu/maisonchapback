package com.tonde.maisonchapback.services.interfaces;

import com.tonde.maisonchapback.models.workflows.Reservation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReservationService {

    public ResponseEntity<List<Reservation>> getAllReservations();

    public ResponseEntity<Reservation> getReservationById(int id);

    public ResponseEntity<?> createReservation(Reservation reservation);

    public ResponseEntity<?> updateReservation(Reservation reservation);

    public ResponseEntity<?> deleteReservation(int id);

    public ResponseEntity<?> getReservationByHouseId(int id);

    public ResponseEntity<?> getReservationByUserId(int id);

    public ResponseEntity<?> getReservationByHouseIdAndUserId(int houseId, int userId);


}
