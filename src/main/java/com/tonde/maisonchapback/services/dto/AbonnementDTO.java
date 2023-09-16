package com.tonde.maisonchapback.services.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link com.tonde.maisonchapback.domains.Abonnement} entity.
 */

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AbonnementDTO implements Serializable {
    private Integer id;

    @NotNull
    private String duree;

    @NotNull
    private String type;

    @NotNull
    private String etat;

    private Instant dateDebut;

    private Instant dateFin;

    @NotNull
    private Instant dateCreation;

    private TypeAbonnementDTO typeAbonnement;


    private UserDTO userDto;
}
