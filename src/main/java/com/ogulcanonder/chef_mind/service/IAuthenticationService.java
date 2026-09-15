package com.ogulcanonder.chef_mind.service;

import com.ogulcanonder.chef_mind.dto.request.DtoLoginRequest;
import com.ogulcanonder.chef_mind.dto.request.DtoRegisterUserRequest;
import com.ogulcanonder.chef_mind.dto.response.DtoAuthLoginResponse;
import com.ogulcanonder.chef_mind.dto.response.DtoRefreshTokenResponse;
import com.ogulcanonder.chef_mind.dto.response.DtoUserResponse;

public interface IAuthenticationService {
    public DtoUserResponse register(DtoRegisterUserRequest dtoRegisterUserRequest);

    public DtoAuthLoginResponse login(DtoLoginRequest dtoLoginRequest);

    public DtoRefreshTokenResponse refreshToken(String refreshToken);

    public String logout(String authHeader);
}
