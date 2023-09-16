package com.tonde.maisonchapback.services.mappers;

import com.tonde.maisonchapback.domains.Favoris;
import com.tonde.maisonchapback.services.dto.FavorisDTO;
import org.mapstruct.*;

import java.util.List;


/**
 * Mapper for the entity {@link Favoris} and its DTO {@link FavorisDTO}.
 */
@Mapper(componentModel = "spring")

public interface FavorisMapper extends EntityMapper<FavorisDTO, Favoris> {

    @Override
    @Mapping(target = "id", ignore = true)
        // Ignorer l'ID lors de la conversion
    Favoris toEntity(FavorisDTO dto);

    @Override
    FavorisDTO toDto(Favoris entity);

    @Override
    List<Favoris> toEntity(List<FavorisDTO> dtoList);

    @Override
    List<FavorisDTO> toDto(List<Favoris> entityList);

    @Override
    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget Favoris entity, FavorisDTO dto);
}
