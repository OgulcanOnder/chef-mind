package com.ogulcanonder.chef_mind.dto.response;

public record DtoAuthLoginResponse(
        String accessToken,
        String refreshToken
) {
}
