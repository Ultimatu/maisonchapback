package com.tonde.maisonchapback.services.dto;


import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link com.tonde.maisonchapback.domains.Abonnement} entity.
 */

@SuppressWarnings("common-java:DuplicatedBlocks")
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

    @NotNull
    private TypeAbonnementDTO typeAbonnement;


    @NotNull
    private UserDTO user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Instant getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Instant dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Instant getDateFin() {
        return dateFin;
    }

    public void setDateFin(Instant dateFin) {
        this.dateFin = dateFin;
    }

    public Instant getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Instant dateCreation) {
        this.dateCreation = dateCreation;
    }

    public TypeAbonnementDTO getTypeAbonnementDTO() {
        return typeAbonnement;
    }

    public void setTypeAbonnementDTO(TypeAbonnementDTO typeAbonnementDTO) {
        this.typeAbonnement = typeAbonnementDTO;
    }

    public UserDTO getUserDTO() {
        return user;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.user = userDTO;
    }
}
