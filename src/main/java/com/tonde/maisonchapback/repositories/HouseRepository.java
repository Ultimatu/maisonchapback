package com.tonde.maisonchapback.repositories;

import com.tonde.maisonchapback.models.workflows.House;
import com.tonde.maisonchapback.models.workflows.Status;
import com.tonde.maisonchapback.models.workflows.TypeHouse;
import com.tonde.maisonchapback.models.workflows.user.User;

import io.swagger.v3.oas.annotations.Hidden;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Hidden
public interface HouseRepository  extends JpaRepository<House, Integer> {

    List<House> findAllByUser(User user);

    List<House> findAllHouseByStatusHouse(Status status);

    List<House> findAllByDisponibility(String disponibility);

    List<House> findAllByCity(String city);

    List<House> findAllByPrice(String price);

    List<House> findAllByTypeHouseAndCityAndPrice(TypeHouse typeHouse, String city, String price);

    @Query(value = "SELECT * FROM _houses " +
            "WHERE title LIKE %:keyword% OR description LIKE %:keyword% OR address LIKE %:keyword% OR city LIKE %:keyword% OR country LIKE %:keyword% OR numberOfRooms LIKE %:keyword% OR numberOfBathrooms LIKE %:keyword% OR numberOfFloors LIKE %:keyword% OR price LIKE %:keyword% OR disponibility LIKE %:keyword% OR surface LIKE %:keyword% OR type_id LIKE %:keyword% OR status_id LIKE %:keyword%",
            nativeQuery = true)
    List<House> searchByAnyWord(@Param("keyword") String keyword);

    //strict search
    @Query(value = "SELECT * FROM _houses " +
            "WHERE title LIKE %:keyword1% AND description LIKE %:keyword2% OR address LIKE %:keyword1% AND city LIKE %:keyword2% OR country LIKE %:keyword1% AND numberOfRooms LIKE %:keyword2% OR numberOfBathrooms LIKE %:keyword1% AND numberOfFloors LIKE %:keyword2% OR price LIKE %:keyword1% AND disponibility LIKE %:keyword2% OR surface LIKE %:keyword1% AND type_id LIKE %:keyword2% OR status_id LIKE %:keyword2%",
            nativeQuery = true)
    List<House> searchByTitleAndDescription(@Param("keyword1") String keyword1, @Param("keyword2") String keyword2);

    //strict search
    @Query(value = "SELECT * FROM _houses " +
            "WHERE title LIKE %:keyword% OR description LIKE %:keyword% OR address LIKE %:keyword% OR city LIKE %:keyword% OR country LIKE %:keyword% OR numberOfRooms LIKE %:keyword% OR numberOfBathrooms LIKE %:keyword% OR numberOfFloors LIKE %:keyword% OR price LIKE %:keyword% OR disponibility LIKE %:keyword% OR surface LIKE %:keyword% OR type_id LIKE %:keyword% OR status_id LIKE %:keyword%",
            nativeQuery = true)

    List<House> searchByTitleOrDescription(@Param("keyword") String keyword);


    //searchByAll(int type, String city, String price, String surface, int status, String rooms)

    @Query(value = "SELECT * FROM _houses " +
            "WHERE type_id LIKE %:type% AND city LIKE %:city% AND price LIKE %:price% AND surface LIKE %:surface% AND status_id LIKE %:status% AND numberOfRooms LIKE %:rooms%",
            nativeQuery = true)
    List<House> searchByAll(@Param("type") int type, @Param("city") String city, @Param("price") String price, @Param("surface") String surface, @Param("status") int status, @Param("rooms") String rooms);


    @Query(value = "SELECT * FROM _houses " +
            "WHERE type_id LIKE %:type% AND price LIKE %:price%",
            nativeQuery = true)
    List<House> getByTypeAndPrice(int type, String price);

    List<House> findAllByTypeHouse(TypeHouse typeHouse);

    List<House> findAllByCityAndPrice(String city, String price);
}
