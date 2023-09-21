package com.tonde.maisonchapback.services.mappers;

import com.tonde.maisonchapback.domains.House;
import com.tonde.maisonchapback.domains.Photo;
import com.tonde.maisonchapback.services.dto.HouseDTO;
import com.tonde.maisonchapback.services.dto.PhotoDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;



/**
 * Mapper for the entity {@link Photo} and its DTO {@link PhotoDTO}.

*/

@Mapper(componentModel = "spring")
public interface PhotoMapper extends EntityMapper<PhotoDTO, Photo> {
 @Mapping(target = "house", source = "house", qualifiedByName = "houseId")
    PhotoDTO toDto(Photo s);

    @Named("houseId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    HouseDTO toDtoHouseId(House house);
}


