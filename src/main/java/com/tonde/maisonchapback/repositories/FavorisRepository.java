package com.tonde.maisonchapback.repositories;

import com.tonde.maisonchapback.models.workflows.Favoris;
import com.tonde.maisonchapback.models.workflows.House;
import com.tonde.maisonchapback.models.workflows.user.User;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


@Hidden
public interface FavorisRepository extends JpaRepository<Favoris, Integer> {
    Optional<Favoris> findByHouseIdAndUser(Integer house_id, User user);

    List<Favoris> findAllByUser(User user);

    List<Favoris> findAllByHouse(House house);
}
