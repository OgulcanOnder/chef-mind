package com.ogulcanonder.chef_mind.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.Map;

public interface IJwtService {
    public String generateAccessToken(UserDetails userDetails);

    public SecretKey getSecretKey();

    public String createToken(Map<String, Object> claims, String email, long expireTime);

    public Claims extractAllClaims(String token);

    public Boolean validateToken(String token, UserDetails userDetails);

    public String generateRefreshToken(UserDetails userDetails);

    public void saveRefreshToken(String email, String refreshToken, long expireTime);

    public String getRefreshToken(String email);

    public void  deleteRefreshToken(String email);
}
