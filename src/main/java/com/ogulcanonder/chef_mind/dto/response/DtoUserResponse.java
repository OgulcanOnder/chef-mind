package com.ogulcanonder.chef_mind.dto.response;

import com.ogulcanonder.chef_mind.role.Role;

import java.util.Set;

public record DtoUserResponse(
        String name,
        String surname,
        String username,
        String email,
        boolean isAccountNonExpired,
        boolean isAccountNonLocked,
        boolean isCredentialsNonExpired,
        boolean isEnabled,
        Set<Role> authorities
) {
}
