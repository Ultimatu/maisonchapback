
package com.tonde.maisonchapback.services.mappers;

import com.tonde.maisonchapback.domains.House;
import com.tonde.maisonchapback.domains.Status;
import com.tonde.maisonchapback.domains.TypeHouse;
import com.tonde.maisonchapback.domains.User;
import com.tonde.maisonchapback.services.dto.HouseDTO;
import com.tonde.maisonchapback.services.dto.StatusDTO;
import com.tonde.maisonchapback.services.dto.TypeHouseDTO;
import com.tonde.maisonchapback.services.dto.UserDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;



/**
 * Mapper for the entity {@link House} and its DTO {@link HouseDTO}.
 */


@Mapper(componentModel = "spring")
public interface HouseMapper extends EntityMapper<HouseDTO, House> {
    @Mapping(target = "user", source = "user", qualifiedByName = "userId")
    @Mapping(target = "typeHouse", source = "typeHouse", qualifiedByName = "typeHouseId")
    @Mapping(target = "statusHouse", source = "statusHouse", qualifiedByName = "statusId")
    HouseDTO toDto(House s);

    @Named("userId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserDTO toDtoUserId(User user);

    @Named("typeHouseId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TypeHouseDTO toDtoTypeHouseId(TypeHouse typeHouse);

    @Named("statusId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    StatusDTO toDtoStatusId(Status status);
}

