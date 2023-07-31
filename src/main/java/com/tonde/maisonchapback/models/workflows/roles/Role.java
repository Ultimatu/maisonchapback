package com.tonde.maisonchapback.models.workflows.roles;


import com.tonde.maisonchapback.models.workflows.roles.Permission;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;

import static com.tonde.maisonchapback.models.workflows.roles.Permission.*;

@RequiredArgsConstructor
public enum Role {

    //emptyset for user
    USER(
            Set.of()
    ),
    PROPRIO(
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
                    PROPRIO_DELETE

            )
    );

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
