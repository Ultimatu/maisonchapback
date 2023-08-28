package com.tonde.maisonchapback.repositories;

import com.tonde.maisonchapback.models.workflows.House;
import com.tonde.maisonchapback.models.workflows.Rates;
import com.tonde.maisonchapback.models.workflows.user.User;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Hidden
public interface RateRepository extends JpaRepository<Rates, Integer> {
    List<Rates> findAllByHouse(House house);

    List<Rates> findAllByUser(User user);

    Rates findByHouseAndUser(House house, User user);
}
