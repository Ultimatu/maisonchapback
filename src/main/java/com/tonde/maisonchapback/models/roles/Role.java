package com.tonde.maisonchapback.models.roles;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;

import static com.tonde.maisonchapback.models.roles.Permission.*;

@RequiredArgsConstructor
public enum Role {


    FREE_USER(
            Set.of(
                    USER_READ,
                    USER_UPDATE,
                    USER_FREE
            )
    ),
    FREE_PROPRIO(
            Set.of(
                    PROPRIO_CREATE,
                    PROPRIO_UPDATE,
                    PROPRIO_READ,
                    PROPRIO_DELETE
            )
    ),
    ADMIN(
            Set.of(
                    ADMIN_READ,
                    ADMIN_CREATE,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,
                    PROPRIO_CREATE,
                    PROPRIO_UPDATE,
                    PROPRIO_READ,
                    PROPRIO_DELETE,
                    USER_READ,
                    USER_UPDATE,
                    USER_DELETE,
                    PROPRIO_PREMIUM,
                    PROPRIO_STANDARD,
                    PROPRIO_FREE,
                    USER_PREMIUM,
                    USER_STANDARD,
                    USER_FREE,
                    SEND_MESSAGE,
                    READ_MESSAGE,
                    DELETE_MESSAGE


            )
    ),
    PREMIUM_USER(
            Set.of(
                    USER_PREMIUM,
                    USER_STANDARD,
                    USER_FREE,
                    SEND_MESSAGE,
                    READ_MESSAGE,
                    DELETE_MESSAGE,
                    FAVORIS_ACCESS,
                    ACTIVE_NOTIFICATION

            )
    ),
    STANDARD_USER(
            Set.of(
                    USER_STANDARD,
                    USER_READ,
                    USER_UPDATE,
                    USER_FREE,
                    FAVORIS_ACCESS,
                    ACTIVE_NOTIFICATION
            )
    ),
    STANDARD_PROPRIO(
            Set.of(
                    PROPRIO_STANDARD,
                    PROPRIO_FREE,
                    ACTIVE_NOTIFICATION,
                    SEND_MESSAGE,
                    FAVORIS_ACCESS,
                    READ_MESSAGE,
                    USER_STANDARD,
                    USER_FREE
            )
    ),
    PREMIUM_PROPRIO(
            Set.of(
                    PROPRIO_FULL_ACCESS,
                    PROPRIO_PREMIUM,
                    PROPRIO_STANDARD,
                    PROPRIO_FREE,
                    USER_PREMIUM,
                    USER_STANDARD,
                    USER_FREE,
                    SEND_MESSAGE,
                    READ_MESSAGE,
                    DELETE_MESSAGE,
                    FAVORIS_ACCESS,
                    ACTIVE_NOTIFICATION
            )
    ),;

    @Getter
    private final Set<Permission> permissions;

    /**
     * Convert the permissions to a list of SimpleGrantedAuthority
     * @return List<SimpleGrantedAuthority>
     */
    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = new java.util.ArrayList<>(
                getPermissions()
                        .stream().map(
                        permission -> new SimpleGrantedAuthority(
                                permission.getPermission()
                        )
                ).toList()
        );
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return authorities;
    }


}
