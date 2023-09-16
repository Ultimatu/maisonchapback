package com.tonde.maisonchapback.services.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.time.Instant;


@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class TypeHouseDTO implements Serializable {

    private Integer id;

    @NotNull(message = "Le type est obligatoire")
    private String type;

    @NotNull(message = "La description est obligatoire")
    private String description;

    private Instant dateCreation;

    private Instant dateModification;
}
