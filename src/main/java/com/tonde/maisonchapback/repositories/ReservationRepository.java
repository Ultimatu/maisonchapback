package com.tonde.maisonchapback.repositories;

import com.tonde.maisonchapback.domains.House;
import com.tonde.maisonchapback.domains.Reservation;
import com.tonde.maisonchapback.domains.User;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Hidden

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Optional<Reservation> findByHouseIdAndUser(Integer house_id, User user);

    Optional<Reservation> findByHouse(House house);

    Optional<Reservation> findByUser(User user);
}
