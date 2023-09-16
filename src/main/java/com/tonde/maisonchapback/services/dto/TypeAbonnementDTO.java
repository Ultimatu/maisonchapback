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
public class TypeAbonnementDTO implements Serializable {
    private Integer id;

    @NotNull
    private String type;

    @NotNull
    private String description;

    @NotNull
    private String access;

    private Instant dateCreation;

    private Instant updatedAt;

}
