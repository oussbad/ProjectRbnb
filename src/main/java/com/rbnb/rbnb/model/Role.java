package com.rbnb.rbnb.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rbnb.rbnb.model.Permission.*;


@RequiredArgsConstructor
public enum Role {

    ROLE_HOST(
            Set.of(
                    Permission.READ,
                    Permission.HOST_UPDATE,
                    Permission.HOST_CREATE,
                    Permission.HOST_DELETE
            )
    ),
    ROLE_CLIENT(
            Set.of(
                    Permission.READ,
                    Permission.CLIENT_UPDATE,
                    Permission.CLIENT_CREATE,
                    Permission.CLIENT_DELETE
            )
    ),
    ROLE_ADMIN(
            Set.of(
                    Permission.READ,
                    Permission.ADMIN_UPDATE,
                    Permission.ADMIN_CREATE,
                    Permission.ADMIN_DELETE
            )
    );




    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}