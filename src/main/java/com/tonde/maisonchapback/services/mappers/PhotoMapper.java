package com.tonde.maisonchapback.services.mappers;

import com.tonde.maisonchapback.domains.Photo;
import com.tonde.maisonchapback.services.dto.PhotoDTO;
import org.mapstruct.*;

import java.util.List;

/**
 * Mapper for the entity {@link Photo} and its DTO {@link PhotoDTO}.
 */
@Mapper(componentModel = "spring")
public interface PhotoMapper extends EntityMapper<PhotoDTO, Photo> {

    @Override
    @Mapping(target = "id", ignore = true)
        // Ignorer l'ID lors de la conversion
    Photo toEntity(PhotoDTO dto);

    @Override
    PhotoDTO toDto(Photo entity);

    @Override
    List<Photo> toEntity(List<PhotoDTO> dtoList);

    @Override
    List<PhotoDTO> toDto(List<Photo> entityList);

    @Override
    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget Photo entity, PhotoDTO dto);
}
