package com.example.StudentMaterialServer.services.IMPL;


import com.example.StudentMaterialServer.entity.RefreshToken;
import com.example.StudentMaterialServer.repositories.RefreshTokenRepository;
import com.example.StudentMaterialServer.repositories.UserRespository;
import com.example.StudentMaterialServer.services.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceimpl implements RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRespository userRespository;



    @Override
    public RefreshToken createRefreshToken(String userName) {
        RefreshToken refreshToken =      RefreshToken.builder().registerUser(userRespository.getUserByEmail(userName))
                       .token(UUID.randomUUID().toString())
                       .expiryDate(Instant.now().plusMillis(600000)).build();

              return refreshTokenRepository.save(refreshToken);
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }


    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token was expired. Please make a new signin request");
        }
        return token;
    }


}
