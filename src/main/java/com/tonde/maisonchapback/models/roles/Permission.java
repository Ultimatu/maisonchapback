package com.tonde.maisonchapback.models.roles;


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

    PROPRIO_PREMIUM("proprio:premium"),
    PROPRIO_STANDARD("proprio:standard"),

    PROPRIO_FULL_ACCESS("proprio:full_access"),
    PROPRIO_FREE("proprio:free"),

    USER_READ("user:read"),
    USER_UPDATE("user:update"),

    USER_PREMIUM("user:premium"),
    USER_STANDARD("user:standard"),
    USER_FREE("user:free"),
    USER_DELETE("access:delete"),

    SEND_MESSAGE("access:send_message"),

    READ_MESSAGE("access:read_message"),
    DELETE_MESSAGE("access:delete_message"),

    FAVORIS_ACCESS("access:favoris_access"),

    ACTIVE_NOTIFICATION("access:active_notification");


    @Getter
    private final String permission;

}
