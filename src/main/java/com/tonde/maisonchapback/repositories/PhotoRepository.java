package com.tonde.maisonchapback.repositories;

import com.tonde.maisonchapback.models.workflows.House;
import com.tonde.maisonchapback.models.workflows.Photo;
import com.tonde.maisonchapback.models.workflows.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {
    List<Photo> findAllByHouse(House house);

    List<Photo> findAllByHouseUser(User user);
}
