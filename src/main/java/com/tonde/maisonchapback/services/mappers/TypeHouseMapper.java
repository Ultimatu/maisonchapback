package com.tonde.maisonchapback.services.mappers;

import com.tonde.maisonchapback.domains.TypeHouse;
import com.tonde.maisonchapback.services.dto.TypeHouseDTO;
import org.mapstruct.*;

import java.util.List;


/**
 * Mapper for the entity {@link TypeHouse} and its DTO {@link TypeHouseDTO}.
 */
@Mapper(componentModel = "spring")
public interface TypeHouseMapper extends EntityMapper<TypeHouseDTO, TypeHouse> {

    @Override
    @Mapping(target = "id", ignore = true)
        // Ignorer l'ID lors de la conversion
    TypeHouse toEntity(TypeHouseDTO dto);

    @Override
    TypeHouseDTO toDto(TypeHouse entity);

    @Override
    List<TypeHouse> toEntity(List<TypeHouseDTO> dtoList);

    @Override
    List<TypeHouseDTO> toDto(List<TypeHouse> entityList);

    @Override
    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget TypeHouse entity, TypeHouseDTO dto);
}
