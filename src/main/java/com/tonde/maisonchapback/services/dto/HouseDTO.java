package com.tonde.maisonchapback.services.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class HouseDTO implements Serializable {

    private Integer id;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private String address;

    @NotNull
    private String city;

    @NotNull
    private String country;

    @NotNull
    private String numberOfRooms;

    @NotNull
    private String numberOfBathrooms;

    @NotNull
    private String numberOfFloors;

    @NotNull
    private String price;

    @NotNull
    private String surface;

    @NotNull
    private String disponibility;

    private UserDTO userDTO;

    private TypeHouseDTO typeHouse;

    private StatusDTO status;
}
