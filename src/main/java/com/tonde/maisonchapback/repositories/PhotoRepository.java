package com.tonde.maisonchapback.repositories;

import com.tonde.maisonchapback.domains.House;
import com.tonde.maisonchapback.domains.Photo;
import com.tonde.maisonchapback.domains.User;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Hidden
public interface PhotoRepository extends JpaRepository<Photo, Integer> {
    List<Photo> findAllByHouse(House house);

    List<Photo> findAllByHouseUser(User user);

    @Transactional
    @Modifying
    @Query(value = "insert into _photos (id,url, description, house_id) values (null, :url, :description, :house_id)", nativeQuery = true)
    void saveAll(@Param("url") String url, @Param("description") String description, @Param("house_id") Integer id);


}
