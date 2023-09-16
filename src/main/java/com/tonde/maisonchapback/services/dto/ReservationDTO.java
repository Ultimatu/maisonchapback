package com.tonde.maisonchapback.services.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.time.Instant;


@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ReservationDTO implements Serializable {
    private Integer id;

    @NotNull
    private Instant startDate;

    @NotNull
    private Instant endDate;

    @NotNull
    private String status;

    private HouseDTO house;

    private UserDTO userDTO;

}
