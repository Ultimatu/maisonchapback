package com.tonde.maisonchapback.services.mappers;

import com.tonde.maisonchapback.domains.Status;
import com.tonde.maisonchapback.services.dto.StatusDTO;
import org.mapstruct.*;

import java.util.List;

/**
 * Mapper for the entity {@link Status} and its DTO {@link StatusDTO}.
 */
@Mapper(componentModel = "spring")
public interface StatusMapper extends EntityMapper<StatusDTO, Status> {

    @Override
    @Mapping(target = "id", ignore = true)
        // Ignorer l'ID lors de la conversion
    Status toEntity(StatusDTO dto);

    @Override
    StatusDTO toDto(Status entity);

    @Override
    List<Status> toEntity(List<StatusDTO> dtoList);

    @Override
    List<StatusDTO> toDto(List<Status> entityList);

    @Override
    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget Status entity, StatusDTO dto);
}
