package com.ogulcanonder.chef_mind.controller;

import com.ogulcanonder.chef_mind.dto.request.DtoRegisterUserRequest;
import com.ogulcanonder.chef_mind.dto.response.DtoUserResponse;
import com.ogulcanonder.chef_mind.service.IAuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final IAuthenticationService authenticationService;

    public AuthenticationController(IAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<DtoUserResponse> register(@Valid @RequestBody DtoRegisterUserRequest dtoRegisterUserRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.register(dtoRegisterUserRequest));
    }
}
