package com.tonde.maisonchapback.services.mappers;

import com.tonde.maisonchapback.domains.Reservation;
import com.tonde.maisonchapback.services.dto.ReservationDTO;
import org.mapstruct.*;

import java.util.List;


/**
 * Mapper for the entity {@link Reservation} and its DTO {@link ReservationDTO}.
 */
@Mapper(componentModel = "spring")
public interface ReservationMapper extends EntityMapper<ReservationDTO, Reservation> {

    @Override
    @Mapping(target = "id", ignore = true)
        // Ignorer l'ID lors de la conversion
    Reservation toEntity(ReservationDTO dto);

    @Override
    ReservationDTO toDto(Reservation entity);

    @Override
    List<Reservation> toEntity(List<ReservationDTO> dtoList);

    @Override
    List<ReservationDTO> toDto(List<Reservation> entityList);

    @Override
    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget Reservation entity, ReservationDTO dto);
}
