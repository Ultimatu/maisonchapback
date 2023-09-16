package com.tonde.maisonchapback.services.mappers;

import com.tonde.maisonchapback.services.dto.RateDTO;
import org.apache.poi.ss.formula.functions.Rate;
import org.mapstruct.*;

import java.util.List;

/**
 * Mapper for the entity {@link Rate} and its DTO {@link RateDTO}.
 */
@Mapper(componentModel = "spring")
public interface RateMapper extends EntityMapper<RateDTO, Rate> {

    @Override
    @Mapping(target = "id", ignore = true)
        // Ignorer l'ID lors de la conversion
    Rate toEntity(RateDTO dto);

    @Override
    RateDTO toDto(Rate entity);

    @Override
    List<Rate> toEntity(List<RateDTO> dtoList);

    @Override
    List<RateDTO> toDto(List<Rate> entityList);

    @Override
    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget Rate entity, RateDTO dto);
}
