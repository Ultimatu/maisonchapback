package com.tonde.maisonchapback.services.mappers;

import com.tonde.maisonchapback.domains.Comments;
import com.tonde.maisonchapback.domains.House;
import com.tonde.maisonchapback.domains.User;
import com.tonde.maisonchapback.services.dto.CommentDTO;
import com.tonde.maisonchapback.services.dto.HouseDTO;
import com.tonde.maisonchapback.services.dto.UserDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


/**
 * Mapper for the entity {@link Comments} and its DTO called {@link CommentDTO}.
 */

@Mapper(componentModel = "spring")
public interface CommentMapper extends EntityMapper<CommentDTO, Comments> {
    @Mapping(target = "house", source = "house", qualifiedByName = "houseId")
    @Mapping(target = "user", source = "user", qualifiedByName = "userId")
    CommentDTO toDto(Comments s);

    @Named("houseId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    HouseDTO toDtoHouseId(House house);

    @Named("userId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserDTO toDtoUserId(User user);
}
