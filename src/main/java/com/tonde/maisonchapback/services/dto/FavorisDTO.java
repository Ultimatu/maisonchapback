package com.tonde.maisonchapback.services.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class FavorisDTO implements Serializable {

    private Integer id;
    private HouseDTO house;
    private UserDTO user;
}
