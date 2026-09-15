package com.ogulcanonder.chef_mind.dto.response;

public record DtoRefreshTokenResponse(
        String newAccessToken,
        String newRefreshToken
) {
}
