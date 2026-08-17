package com.ogulcanonder.chef_mind.service;

import com.ogulcanonder.chef_mind.dto.request.DtoRegisterUserRequest;
import com.ogulcanonder.chef_mind.dto.response.DtoUserResponse;

public interface IAuthenticationService {
    public DtoUserResponse register(DtoRegisterUserRequest dtoRegisterUserRequest);
}
