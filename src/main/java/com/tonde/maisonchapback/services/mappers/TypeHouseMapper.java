package com.tonde.maisonchapback.services.mappers;

import com.tonde.maisonchapback.domains.TypeHouse;
import com.tonde.maisonchapback.services.dto.TypeHouseDTO;
import org.mapstruct.*;




/**
 * Mapper for the entity {@link TypeHouse} and its DTO {@link TypeHouseDTO}.
 */

@Mapper(componentModel = "spring")
public interface TypeHouseMapper extends EntityMapper<TypeHouseDTO, TypeHouse> {}
