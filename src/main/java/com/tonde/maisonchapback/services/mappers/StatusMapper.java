package com.tonde.maisonchapback.services.mappers;

import com.tonde.maisonchapback.domains.Status;
import com.tonde.maisonchapback.services.dto.StatusDTO;
import org.mapstruct.*;



/**
 * Mapper for the entity {@link Status} and its DTO {@link StatusDTO}.
 */

@Mapper(componentModel = "spring")
public interface StatusMapper extends EntityMapper<StatusDTO, Status> {}
