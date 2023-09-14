package com.tonde.maisonchapback.services.implementation;

import com.tonde.maisonchapback.models.workflows.House;
import com.tonde.maisonchapback.models.workflows.Reservation;
import com.tonde.maisonchapback.models.workflows.user.User;
import com.tonde.maisonchapback.repositories.HouseRepository;
import com.tonde.maisonchapback.repositories.ReservationRepository;
import com.tonde.maisonchapback.repositories.UserRepository;
import com.tonde.maisonchapback.services.interfaces.ReservationService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Hidden
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final HouseRepository houseRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return reservationRepository.findAll().isEmpty() ? null : ResponseEntity.ok(reservationRepository.findAll());
    }

    @Override
    public ResponseEntity<Reservation> getReservationById(int id) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        return reservationOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().body(null));

    }

    @Override
    public ResponseEntity<String> createReservation(Reservation reservation) {
        Optional<Reservation> optional = reservationRepository
                .findByHouseIdAndUser(reservation.getHouse().getId(), reservation.getUser());
        if (optional.isPresent()) {
            return ResponseEntity.badRequest().body("Reservation already exists");
        }
        reservationRepository.save(reservation);
        return ResponseEntity.ok("Reservation created");
    }

    @Override
    public ResponseEntity<String> updateReservation(Reservation reservation) {
        Optional<Reservation> optional = reservationRepository.findById(reservation.getId());
        if (optional.isPresent()) {
            Reservation reservation1 = optional.get();
            reservation1.setHouse(reservation.getHouse());
            reservation1.setStartDate(reservation.getStartDate());
            reservation1.setEndDate(reservation.getEndDate());
            reservation1.setStatus(reservation.getStatus());
            reservationRepository.save(reservation1);
            return ResponseEntity.ok("Reservation updated");
        }
        return ResponseEntity.badRequest().body("Impossible to update this reservation");
    }

    @Override
    public ResponseEntity<?> deleteReservation(int id) {
        Optional<Reservation> optional = reservationRepository.findById(id);
        if (optional.isPresent()) {
            reservationRepository.delete(optional.get());
            return ResponseEntity.ok("Reservation deleted");
        }
        return ResponseEntity.badRequest().body("Impossible to delete this reservation");
    }

    @Override
    public ResponseEntity<?> getReservationByHouseId(int id) {
        Optional<House> optional = houseRepository.findById(id);
        if (optional.isPresent()) {
            return reservationRepository.findByHouse(optional.get()).isEmpty() ? null : ResponseEntity.ok(reservationRepository.findByHouse(optional.get()));
        }
        return ResponseEntity.badRequest().body("Impossible to find this house");

    }

    @Override
    public ResponseEntity<?> getReservationByUserId(int id) {
        Optional<User> optional = userRepository.findById(id);
        return optional.filter(user -> reservationRepository.findByUser(user).isPresent()).map(user -> ResponseEntity.ok(reservationRepository.findByUser(user))).orElse(null);
    }

    @Override
    public ResponseEntity<?> getReservationByHouseIdAndUserId(int houseId, int userId) {
        Optional<House> optionalHouse = houseRepository.findById(houseId);
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalHouse.isPresent() && optionalUser.isPresent()) {
            Optional<Reservation> optionalReservation = reservationRepository.findByHouseIdAndUser(houseId, optionalUser.get());
            return optionalReservation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().body(null));
        }
        return null;
    }
}
