package com.tonde.maisonchapback.services.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Instant;


public class StatusDTO implements Serializable {

    private Integer id;

    @NotNull(message = "Status is required")
    private String status;

    @NotNull
    private String description;

    private Instant dateCreation;

    private Instant updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
