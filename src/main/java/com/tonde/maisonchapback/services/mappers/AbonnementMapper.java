package com.tonde.maisonchapback.services.mappers;

import com.tonde.maisonchapback.domains.Abonnement;
import com.tonde.maisonchapback.domains.TypeAbonnement;
import com.tonde.maisonchapback.domains.User;
import com.tonde.maisonchapback.services.dto.AbonnementDTO;
import com.tonde.maisonchapback.services.dto.TypeAbonnementDTO;
import com.tonde.maisonchapback.services.dto.UserDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Mapper for the entity {@link Abonnement} and its DTO {@link AbonnementDTO}.
 */
@Mapper(componentModel = "spring")
public interface AbonnementMapper extends EntityMapper<AbonnementDTO, Abonnement> {
    @Mapping(target = "typeAbonnementDTO", source = "typeAbonnement", qualifiedByName = "typeAbonnementId")
    @Mapping(target = "userDTO", source = "user", qualifiedByName = "userId")
    AbonnementDTO toDto(Abonnement s);

    @Named("typeAbonnementId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TypeAbonnementDTO toDtoTypeAbonnementId(TypeAbonnement typeAbonnement);

    @Named("userId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserDTO toDtoUserId(User user);
}
