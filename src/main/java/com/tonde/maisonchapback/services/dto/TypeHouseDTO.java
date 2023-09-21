package com.tonde.maisonchapback.services.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Instant;



public class TypeHouseDTO implements Serializable {

    private Integer id;

    @NotNull(message = "Le type est obligatoire")
    private String type;

    @NotNull(message = "La description est obligatoire")
    private String description;

    private Instant dateCreation;

    private Instant dateModification;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Instant dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Instant getDateModification() {
        return dateModification;
    }

    public void setDateModification(Instant dateModification) {
        this.dateModification = dateModification;
    }
}
