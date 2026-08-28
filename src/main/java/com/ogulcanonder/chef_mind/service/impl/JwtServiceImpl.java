package com.ogulcanonder.chef_mind.service.impl;

import com.ogulcanonder.chef_mind.exception.ResourceNotFoundException;
import com.ogulcanonder.chef_mind.model.RefreshToken;
import com.ogulcanonder.chef_mind.model.User;
import com.ogulcanonder.chef_mind.repository.RefreshTokenRepository;
import com.ogulcanonder.chef_mind.service.IJwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class JwtServiceImpl implements IJwtService {
    @Value("${jwt.key}")
    private String JWT_KEY;

    private final RefreshTokenRepository refreshTokenRepository;

    private static final long ACCESS_TOKEN_VALIDITY_SECONDS = 900000L;
    private static final long REFRESH_TOKEN_VALIDITY_SECONDS = 604800000L;

    public JwtServiceImpl(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public String generateAccessToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", ((User) userDetails).getId());
        claims.put("username", ((User) userDetails).getRealUsername());
        claims.put("roles", userDetails.getAuthorities());
        return createToken(claims, userDetails.getUsername(), ACCESS_TOKEN_VALIDITY_SECONDS);
    }

    @Override
    public SecretKey getSecretKey() {
        byte[] secretKey = Decoders.BASE64.decode(JWT_KEY);
        return Keys.hmacShaKeyFor(secretKey);
    }

    @Override
    public String createToken(Map<String, Object> claims, String email, long expireTime) {
        return Jwts.builder()
                .claims(claims)
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(getSecretKey(), Jwts.SIG.HS256)
                .compact();
    }

    @Override
    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    @Override
    public Boolean validateToken(String token, UserDetails userDetails) {
        String email = extractAllClaims(token).getSubject();
        Date expiration = extractAllClaims(token).getExpiration();
        return userDetails.getUsername().equals(email) && expiration.after(new Date());
    }

    @Override
    public String generateRefreshToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", ((User) userDetails).getId());
        String refreshToken = createToken(claims, userDetails.getUsername(), REFRESH_TOKEN_VALIDITY_SECONDS);
        saveRefreshToken(userDetails.getUsername(), refreshToken, REFRESH_TOKEN_VALIDITY_SECONDS);
        return refreshToken;
    }

    @Transactional
    @Override
    public void saveRefreshToken(String email, String refreshToken, long expireTime) {
        RefreshToken saveRefreshToken = refreshTokenRepository.findByEmail(email)
                .orElseGet(RefreshToken::new);
        saveRefreshToken.setEmail(email);
        saveRefreshToken.setRefreshToken(refreshToken);
        LocalDateTime expiresAt = LocalDateTime.now().plus(Duration.ofMillis(expireTime));
        saveRefreshToken.setExpirationTime(expiresAt);
        refreshTokenRepository.save(saveRefreshToken);
    }

    @Override
    public String getRefreshToken(String email) {
        RefreshToken refreshToken = refreshTokenRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Refresh token not found"));
        return refreshToken.getRefreshToken();
    }

    @Transactional
    @Override
    public void deleteRefreshToken(String email) {
        refreshTokenRepository.deleteRefreshTokenByEmail(email);
    }
}
