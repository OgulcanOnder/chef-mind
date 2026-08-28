package com.ogulcanonder.chef_mind.service.impl;

import com.ogulcanonder.chef_mind.dto.request.DtoLoginRequest;
import com.ogulcanonder.chef_mind.dto.request.DtoRegisterUserRequest;
import com.ogulcanonder.chef_mind.dto.response.DtoAuthLoginResponse;
import com.ogulcanonder.chef_mind.dto.response.DtoRefreshTokenResponse;
import com.ogulcanonder.chef_mind.dto.response.DtoUserResponse;
import com.ogulcanonder.chef_mind.exception.ResourceAlreadyExistsException;
import com.ogulcanonder.chef_mind.model.User;
import com.ogulcanonder.chef_mind.role.Role;
import com.ogulcanonder.chef_mind.service.IAuthenticationService;
import com.ogulcanonder.chef_mind.service.IJwtService;
import com.ogulcanonder.chef_mind.service.IUserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.Objects;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class AuthenticationServiceImpl implements IAuthenticationService {
    private final IUserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final IJwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;

    private static final int AUTH_HEADER_SIZE = 7;


    public AuthenticationServiceImpl(IUserService userService, PasswordEncoder passwordEncoder,
                                     AuthenticationManager authenticationManager, IJwtService jwtService,
                                     UserDetailsServiceImpl userDetailsService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
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
            throw new ResourceAlreadyExistsException("Email already exists");
        }
        if (userService.existsByUsername(username)) {
            throw new ResourceAlreadyExistsException("Username already exists");
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

    @Transactional
    @Override
    public DtoAuthLoginResponse login(DtoLoginRequest dtoLoginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dtoLoginRequest.email(), dtoLoginRequest.password()));
        if (authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String accessToken = jwtService.generateAccessToken(userDetails);
            String refreshToken = jwtService.generateRefreshToken(userDetails);
            return new DtoAuthLoginResponse(accessToken, refreshToken);
        }
        throw new BadCredentialsException("Invalid email or password");
    }

    @Transactional
    @Override
    public DtoRefreshTokenResponse refreshToken(String refreshToken) {
        String token = refreshToken.substring(AUTH_HEADER_SIZE);
        String email = jwtService.extractAllClaims(token).getSubject();
        String storedToken = jwtService.getRefreshToken(email);
        if (!Objects.equals(storedToken, token)) {
            throw new BadCredentialsException("Invalid refresh token");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        String newAccessToken = jwtService.generateAccessToken(userDetails);
        String newRefreshToken = jwtService.generateRefreshToken(userDetails);
        return new DtoRefreshTokenResponse(newAccessToken, newRefreshToken);
    }

    @Transactional
    @Override
    public String logout(String authHeader) {
        String token = authHeader.substring(AUTH_HEADER_SIZE);
        String email = jwtService.extractAllClaims(token).getSubject();
        jwtService.deleteRefreshToken(email);
        return "Logged out";
    }
}
