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
public class CommentDTO implements Serializable {

    private Integer id;

    @NotNull
    private String comment;

    @NotNull
    private Instant dateCreation;

    private Instant dateModification;
    private HouseDTO house;


}
