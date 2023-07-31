package com.tonde.maisonchapback.models.workflows.roles;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),

    ADMIN_DELETE("admin:delete"),

    PROPRIO_READ("proprio:read"),
    PROPRIO_UPDATE("proprio:update"),
    PROPRIO_CREATE("proprio:create"),
    PROPRIO_DELETE("proprio:delete"),

    USER_READ("user:read"),
    USER_UPDATE("user:update");


    @Getter
    private final String permission;

}
