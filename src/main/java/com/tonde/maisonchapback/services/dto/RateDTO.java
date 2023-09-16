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
public class RateDTO implements Serializable {

    private Integer id;

    @NotNull
    private String rate;

    @NotNull
    private String description;

    private HouseDTO house;
}
