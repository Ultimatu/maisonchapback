package com.tonde.maisonchapback.services.mappers;

import com.tonde.maisonchapback.domains.House;
import com.tonde.maisonchapback.domains.Rates;
import com.tonde.maisonchapback.domains.User;
import com.tonde.maisonchapback.services.dto.HouseDTO;
import com.tonde.maisonchapback.services.dto.RateDTO;
import com.tonde.maisonchapback.services.dto.UserDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


/**
 * Mapper for the entity {@link com.tonde.maisonchapback.domains.Rates} and its DTO {@link RateDTO}.
 */
@Mapper(componentModel = "spring")
public interface RateMapper extends EntityMapper<RateDTO, Rates> {
    @Mapping(target = "house", source = "house", qualifiedByName = "houseId")
    @Mapping(target = "user", source = "user", qualifiedByName = "userId")
    RateDTO toDto(Rates s);

    @Named("houseId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    HouseDTO toDtoHouseId(House house);

    @Named("userId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserDTO toDtoUserId(User applicationUser);
}

