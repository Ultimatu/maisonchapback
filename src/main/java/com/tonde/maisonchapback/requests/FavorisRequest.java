package com.tonde.maisonchapback.requests;


import jakarta.persistence.*;
import lombok.*;

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
