package com.ogulcanonder.chef_mind.service.impl;

import com.ogulcanonder.chef_mind.dto.request.DtoRegisterUserRequest;
import com.ogulcanonder.chef_mind.dto.response.DtoUserResponse;
import com.ogulcanonder.chef_mind.model.User;
import com.ogulcanonder.chef_mind.role.Role;
import com.ogulcanonder.chef_mind.service.IAuthenticationService;
import com.ogulcanonder.chef_mind.service.IUserService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class AuthenticationServiceImpl implements IAuthenticationService {
    private final IUserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationServiceImpl(IUserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public DtoUserResponse register(DtoRegisterUserRequest dtoRegisterUserRequest) {
        Locale trLocale = Locale.of("tr", "TR");
        String name = dtoRegisterUserRequest.name().trim().toUpperCase(trLocale);
        String surname = dtoRegisterUserRequest.surname().trim().toUpperCase(trLocale);
        String username = dtoRegisterUserRequest.username().trim().toLowerCase(Locale.ENGLISH);
        String email = dtoRegisterUserRequest.email().trim().toLowerCase(Locale.ENGLISH);
        if (userService.existsByEmail(email)) {
            throw new BadCredentialsException("Email already exists");
        }
        if (userService.existsByUsername(username)) {
            throw new BadCredentialsException("Username already exists");
        }
        User user = User.builder()
                .name(name)
                .surname(surname)
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(dtoRegisterUserRequest.password()))
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .authorities(Set.of(Role.ROLE_USER))
                .build();
        userService.create(user);
        return new DtoUserResponse(user.getName(), user.getSurname(), user.getRealUsername(), user.getEmail(),
                user.isAccountNonExpired(), user.isAccountNonLocked(), user.isCredentialsNonExpired(), user.isEnabled(),
                user.getAuthorities());
    }
}
