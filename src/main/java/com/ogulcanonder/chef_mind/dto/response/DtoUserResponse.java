package com.ogulcanonder.chef_mind.dto.response;

import com.ogulcanonder.chef_mind.role.Role;
import lombok.AllArgsConstructor;

import java.util.Set;

@AllArgsConstructor
public class DtoUserResponse {
    private String name;
    private String surname;
    private String username;
    private String email;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
    private Set<Role> authorities;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public Set<Role> getAuthorities() {
        return authorities;
    }
}
