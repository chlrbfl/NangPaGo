package com.mars.common.dto.auth;

import com.mars.common.model.auth.RefreshToken;

import java.time.LocalDateTime;

public record RefreshTokenDto(
    String email,
    String refreshToken,
    LocalDateTime expiration
) {
    public static RefreshTokenDto from(RefreshToken refreshToken) {
        return new RefreshTokenDto(
            refreshToken.getEmail(),
            refreshToken.getRefreshToken(),
            refreshToken.getExpiration()
        );
    }

    public RefreshToken toEntity() {
        return RefreshToken.create(this.email, this.refreshToken, this.expiration);
    }
}
