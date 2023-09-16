package com.tonde.maisonchapback.services.mappers;

import com.tonde.maisonchapback.domains.TypeAbonnement;
import com.tonde.maisonchapback.services.dto.TypeAbonnementDTO;
import org.mapstruct.*;

import java.util.List;


/**
 * Mapper for the entity {@link TypeAbonnement} and its DTO {@link TypeAbonnementDTO}.
 */
@Mapper(componentModel = "spring")
public interface TypeAbonnementMapper extends EntityMapper<TypeAbonnementDTO, TypeAbonnement> {

    @Override
    @Mapping(target = "id", ignore = true)
        // Ignorer l'ID lors de la conversion
    TypeAbonnement toEntity(TypeAbonnementDTO dto);

    @Override
    TypeAbonnementDTO toDto(TypeAbonnement entity);

    @Override
    List<TypeAbonnement> toEntity(List<TypeAbonnementDTO> dtoList);

    @Override
    List<TypeAbonnementDTO> toDto(List<TypeAbonnement> entityList);

    @Override
    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget TypeAbonnement entity, TypeAbonnementDTO dto);
}
