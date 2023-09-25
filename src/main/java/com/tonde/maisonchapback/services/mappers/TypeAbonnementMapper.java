package com.tonde.maisonchapback.services.mappers;

import com.tonde.maisonchapback.domains.TypeAbonnement;
import com.tonde.maisonchapback.services.dto.TypeAbonnementDTO;
import org.mapstruct.Mapper;


/**
 * Mapper for the entity {@link TypeAbonnement} and its DTO {@link TypeAbonnementDTO}.
 */

@Mapper(componentModel = "spring")
public interface TypeAbonnementMapper extends EntityMapper<TypeAbonnementDTO, TypeAbonnement> {
}

