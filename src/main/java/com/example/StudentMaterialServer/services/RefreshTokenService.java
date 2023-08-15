package com.example.StudentMaterialServer.services;

import com.example.StudentMaterialServer.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {


    public RefreshToken createRefreshToken(String userName);

    public Optional<RefreshToken> findByToken(String token);
    public RefreshToken verifyExpiration(RefreshToken token);
}
