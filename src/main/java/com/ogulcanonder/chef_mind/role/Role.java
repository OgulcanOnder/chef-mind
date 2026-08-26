package com.ogulcanonder.chef_mind.role;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_USER;

    @Override
    public @Nullable String getAuthority() {
        return name();
    }
}
