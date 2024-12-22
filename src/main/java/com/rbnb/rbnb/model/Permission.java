package com.rbnb.rbnb.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    READ("read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    ADMIN_READ("admin:read"),
    CLIENT_UPDATE("client:update"),
    CLIENT_CREATE("client:create"),
    CLIENT_DELETE("client:delete"),
    CLIENT_READ("client:read"),
    HOST_UPDATE("host:update"),
    HOST_CREATE("host:create"),
    HOST_DELETE("host:delete"),
    HOST_READ("host:read")
    ;

    @Getter
    private final String permission;
}