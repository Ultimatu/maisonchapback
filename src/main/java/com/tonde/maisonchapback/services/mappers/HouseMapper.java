package com.tonde.maisonchapback.services.mappers;

import com.tonde.maisonchapback.domains.House;
import com.tonde.maisonchapback.services.dto.HouseDTO;
import org.mapstruct.*;

import java.util.List;

/**
 * Mapper for the entity {@link House} and its DTO {@link HouseDTO}.
 */
@Mapper(componentModel = "spring")
public interface HouseMapper extends EntityMapper<HouseDTO, House> {

    @Override
    @Mapping(target = "id", ignore = true)
        // Ignorer l'ID lors de la conversion
    House toEntity(HouseDTO dto);

    @Override
    HouseDTO toDto(House entity);

    @Override
    List<House> toEntity(List<HouseDTO> dtoList);

    @Override
    List<HouseDTO> toDto(List<House> entityList);

    @Override
    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget House entity, HouseDTO dto);
}
