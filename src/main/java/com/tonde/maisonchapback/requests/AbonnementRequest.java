package com.tonde.maisonchapback.requests;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class AbonnementRequest {
    @Column(nullable = false)
    private Integer idType;

    @Column(nullable = false)
    private String duree;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String etat;

    @Column(nullable = false)
    private LocalDateTime dateDebut;

    @Column(nullable = false)
    private LocalDateTime dateFin;

    private LocalDateTime dateCreation;


    private Integer idUser;
}
