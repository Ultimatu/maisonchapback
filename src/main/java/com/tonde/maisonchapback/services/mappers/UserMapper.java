package com.tonde.maisonchapback.services.mappers;


import com.tonde.maisonchapback.domains.User;
import com.tonde.maisonchapback.services.dto.UserDTO;
import org.mapstruct.*;

import java.util.List;

/**
 * Mapper for the entity {@link User} and its DTO called {@link UserDTO}.
 * *
 */

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDTO, User> {

    @Override
    @Mapping(target = "id", ignore = true)
        // Ignorer l'ID lors de la conversion
    User toEntity(UserDTO dto);

    @Override
    UserDTO toDto(User entity);

    @Override
    List<User> toEntity(List<UserDTO> dtoList);

    @Override
    List<UserDTO> toDto(List<User> entityList);

    @Override
    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget User entity, UserDTO dto);
}
