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
public class CommentRequest {


    @Column(nullable = false)
    private Integer idHouse;

    @Column(nullable = false)
    private Integer idUser;


    @Column(nullable = false)
    private String comment;

    private LocalDateTime dateCreation;

    private LocalDateTime dateModification;
}
