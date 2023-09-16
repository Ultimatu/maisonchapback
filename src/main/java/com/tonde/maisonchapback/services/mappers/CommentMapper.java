package com.tonde.maisonchapback.services.mappers;

import com.tonde.maisonchapback.domains.Comments;
import com.tonde.maisonchapback.services.dto.CommentDTO;
import org.mapstruct.*;

import java.util.List;

/**
 * Mapper for the entity {@link Comments} and its DTO called {@link CommentDTO}.
 */

@Mapper(componentModel = "spring")
public interface CommentMapper extends EntityMapper<CommentDTO, Comments> {

    @Override
    @Mapping(target = "id", ignore = true)
        // Ignorer l'ID lors de la conversion
    Comments toEntity(CommentDTO dto);

    @Override
    CommentDTO toDto(Comments entity);

    @Override
    List<Comments> toEntity(List<CommentDTO> dtoList);

    @Override
    List<CommentDTO> toDto(List<Comments> entityList);

    @Override
    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget Comments entity, CommentDTO dto);
}
