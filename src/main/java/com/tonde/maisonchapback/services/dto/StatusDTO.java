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
public class StatusDTO implements Serializable {

    private Integer id;

    @NotNull(message = "Status is required")
    private String status;

    @NotNull
    private String description;

    private Instant dateCreation;

    private Instant updatedAt;
}
