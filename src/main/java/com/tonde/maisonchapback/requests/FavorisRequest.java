package com.tonde.maisonchapback.requests;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class FavorisRequest {

    @Column(nullable = false)
    private Integer idHouse;

    @Column(nullable = false)
    private Integer idUser;
}
