package com.tonde.maisonchapback.repositories;

import com.tonde.maisonchapback.models.workflows.House;
import com.tonde.maisonchapback.models.workflows.Reservation;
import com.tonde.maisonchapback.models.workflows.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Optional<Reservation> findByHouseIdAndUser(Integer house_id, User user);

    Optional<Reservation> findByHouse(House house);

    Optional<Reservation> findByUser(User user);
}
